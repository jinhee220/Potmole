package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pothole;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //Stubbed out to avoid errors, not yet implemented
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
                "(user_id, latitude, longitude, current_status, reported_date, inspected_date, repaired_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING pothole_id;";

        try {
             int potholeId =
                     jdbcTemplate.queryForObject(sql, int.class, newPothole.getUserId(), newPothole.getLatitude(),
                             newPothole.getLongitude(), newPothole.getCurrentStatus(),
                             newPothole.getReportedDate(), newPothole.getInspectedDate(), newPothole.getRepairedDate());

             createdPothole = getPotholeById(potholeId);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }
        return createdPothole;
    }

    private Pothole mapRowToPothole (SqlRowSet rs) {

        Pothole p = new Pothole();

        p.setPotHoleId(rs.getInt("pothole_id"));
        p.setUserId(rs.getInt("user_id"));
        p.setLatitude(rs.getBigDecimal("latitude"));
        p.setLongitude(rs.getBigDecimal("longitude"));
        p.setCurrentStatus(rs.getString("current_status"));
        p.setReportedDate(rs.getString("reported_date"));
        p.setInspectedDate(rs.getString("inspected_date"));
        p.setRepairedDate(rs.getString("repaired_date"));

        return p;
    }

    //Map to DTO

}
