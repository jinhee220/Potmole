package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Appointment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcAppointmentDao implements AppointmentDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Appointment getAppointmentById(int id) {
        Appointment returnedAppointment = null;

        String sql = "SELECT * FROM appointments WHERE appointment_id = ?;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            if(results.next()) {

                returnedAppointment = mapRowToAppointment(results);

            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        }

        return returnedAppointment;
    }

    public Appointment updateAppointment (Appointment updatedAppointment) {
        Appointment newAppointment = null;                                              // is this a problem?  why does it read as redundant?

        String sql = "UPDATE appointments SET completion_status = ? WHERE appointment_id = ?;";

        try {

            int numberOfRows = jdbcTemplate.update(sql, updatedAppointment.getCompletionStatus(), updatedAppointment.getAppointmentId());

            if(numberOfRows == 0) {
                throw new DaoException("Update failed, no change to rows in database.");
            } else {
                newAppointment = getAppointmentById(updatedAppointment.getAppointmentId());
            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return newAppointment;
    }

    public int deleteAppointmentById (int id) {
        int numberOfRows = 0;

        String sql = "DELETE FROM appointments WHERE appointment_id = ?;";

        try {

            numberOfRows = jdbcTemplate.update(sql, id);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return numberOfRows;
    }


    public Appointment createAppointment(Appointment newAppointment) {
        Appointment createdAppointment = null;

        String sql = "INSERT INTO appointments " +
                "(appointment_id, pothole_id, user_id, appointment_type, appointment_date, completion_status) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING appointment_id;";

        try {
            int appointmentId =
                    jdbcTemplate.queryForObject(sql, int.class, newAppointment.getAppointmentId(), newAppointment.getPotHoleId(),
                            newAppointment.getUserId(), newAppointment.getAppointmentType(), newAppointment.getAppointmentDate(),
                            newAppointment.getCompletionStatus());

            createdAppointment = getAppointmentById(appointmentId);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

        return createdAppointment;
    }


    private Appointment mapRowToAppointment (SqlRowSet rs) {

        Appointment a = new Appointment();

        a.setAppointmentId(rs.getInt("appointment_id"));
        a.setPotHoleId(rs.getInt("pothole_id"));
        a.setUserId(rs.getInt("user_id"));
        a.setAppointmentType(rs.getString("appointment_type"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setCompletionStatus(rs.getString("completion_status"));

        return a;

    }






}
