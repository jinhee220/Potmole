package com.techelevator.dao;

import com.techelevator.model.Appointment;

public interface AppointmentDao {

    Appointment getAppointmentById (int id);

    Appointment updateAppointment (Appointment updatedAppointment);

    int deleteAppointmentById (int id);

    Appointment createAppointment(Appointment newAppointment);

}
