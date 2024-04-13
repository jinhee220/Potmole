package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pothole;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //
    // DO WE NEED A COMPONENT OVERRIDE HERE?

    //Stubbed out to avoid errors, not yet implemented
    public List<Pothole> getListOfPotholes(){
        List<Pothole> potholes = new ArrayList<>();
        String sql = "SELECT * FROM potholes WHERE current_status != 'Deleted';";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Pothole pothole = mapRowToPothole(results);
                potholes.add(pothole);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return potholes;
    }

    // get pothole by lat lon for employeeForm - might not need
    public Pothole getPotholeIdByLatLon (BigDecimal lat, BigDecimal lon) {
        Pothole pothole = null;
        String sql = "SELECT pothole_id FROM potholes WHERE latitude = ? AND longitude = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, lat, lon);
            while (results.next()) {

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }


        return pothole;
    }


    @Override
    public Pothole getPotholeById (int id){
        Pothole pothole = null;

        String sql = "SELECT * FROM potholes WHERE pothole_id = ?;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            if (results.next()){

                pothole = mapRowToPothole(results);

            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        }

        return pothole;
    }

    @Override
    public Pothole createPothole (Pothole newPothole){

        Pothole createdPothole = null;

        String sql = "INSERT INTO potholes " +
                "(user_id, latitude, longitude, street_address, current_status, reported_date, inspected_date, repaired_date, severity) " +     // added severity to list
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +                                                                            // add another "?" for severity
                "RETURNING pothole_id;";

        try {
             int potholeId =
                     jdbcTemplate.queryForObject(sql, int.class, newPothole.getUserId(), newPothole.getLatitude(),
                             newPothole.getLongitude(), newPothole.getStreetAddress(), newPothole.getCurrentStatus(),
                             newPothole.getReportedDate(), newPothole.getInspectedDate(), newPothole.getRepairedDate(),
                             newPothole.getSeverity());                                                                          // added severity property

             createdPothole = getPotholeById(potholeId);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return createdPothole;
    }

    @Override
    public Pothole updatePothole (Pothole updatedPothole) {

        Pothole newPothole = null;

        String sql = "UPDATE potholes " +
                "SET current_status = ?, inspected_date = ?, repaired_date = ?, severity = ? " +
                "WHERE pothole_id = ?;";

        try {
            String status = updatedPothole.getCurrentStatus();
            String inspected = updatedPothole.getInspectedDate();
            String repaired = updatedPothole.getRepairedDate();
            String severity = updatedPothole.getSeverity();
            int id = updatedPothole.getPotHoleId();

            int numberOfRows = jdbcTemplate.update(sql, status, inspected, repaired, severity, id);

            if(numberOfRows == 0) {

                throw new DaoException( "Update failed, no change to rows in database.");

            } else {

                newPothole = getPotholeById(updatedPothole.getPotHoleId());

            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return newPothole;
    }



    @Override
    public int deletePotholeById (int id) {
        int numberOfRows = 0;

        String sql = "DELETE FROM potholes WHERE pothole_id = ?;";

        try {

            numberOfRows = jdbcTemplate.update(sql, id);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return numberOfRows;
    }







    private Pothole mapRowToPothole (SqlRowSet rs) {

        Pothole p = new Pothole();

        p.setPotHoleId(rs.getInt("pothole_id"));
        p.setUserId(rs.getInt("user_id"));
        p.setLatitude(rs.getBigDecimal("latitude"));
        p.setLongitude(rs.getBigDecimal("longitude"));
        p.setStreetAddress(rs.getString("street_address"));
        p.setCurrentStatus(rs.getString("current_status"));
        p.setReportedDate(rs.getString("reported_date"));
        p.setInspectedDate(rs.getString("inspected_date"));
        p.setRepairedDate(rs.getString("repaired_date"));
        p.setSeverity(rs.getString("severity"));                                                                            // adding severity to map to row function

        return p;
    }

    //Map to DTO

}
