package com.techelevator.service;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentService {

    private AppointmentDao appointmentDao;
    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public Appointment createAppointment(Appointment newAppointment) {
        return appointmentDao.createAppointment(newAppointment);
    }

    public Appointment getAppointmentById (int id) {
        return appointmentDao.getAppointmentById(id);
    }

    public Appointment updateAppointment(Appointment updatedAppointment) {
        return appointmentDao.updateAppointment(updatedAppointment);
    }

    public int deleteAppointmentById (int id) {
        return appointmentDao.deleteAppointmentById(id);
    }

}
