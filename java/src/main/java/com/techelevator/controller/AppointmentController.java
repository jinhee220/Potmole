package com.techelevator.controller;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Appointment;
import com.techelevator.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createAppointment", method = RequestMethod.POST)
    public Appointment createAppointment(@Valid @RequestBody Appointment newAppointment) {

        try {

            return appointmentService.createAppointment(newAppointment);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }

    }

    @RequestMapping(path = "/getAppointment", method = RequestMethod.GET)
    public Appointment getAppointmentById(int id) {

        try {

            return appointmentService.getAppointmentById(id);

        } catch (DaoException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @RequestMapping(path = "/updateAppointment", method = RequestMethod.PUT)
    public Appointment updateAppointment (@Valid @RequestBody Appointment updatedAppointment) {

        try {

            return appointmentService.updateAppointment(updatedAppointment);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deleteAppointment", method = RequestMethod.DELETE)
    public int deleteAppointmentById (int id) {

        try {

            return appointmentService.deleteAppointmentById(id);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }

    }

}
