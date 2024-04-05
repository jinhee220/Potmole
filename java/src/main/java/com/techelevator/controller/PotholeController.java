package com.techelevator.controller;
import com.techelevator.dao.PotholeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import com.techelevator.security.jwt.TokenProvider;
import com.techelevator.service.PotholeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@CrossOrigin
public class PotholeController {

    private PotholeService potholeService;

    public PotholeController(PotholeService potholeService) {
        this.potholeService = potholeService;
    }

    // Request Mappings


    @RequestMapping(path = "/pothole", method = RequestMethod.GET)
    public Pothole getPotholes () {
        Pothole newPothole = new Pothole();
        return newPothole;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/reportPothole", method = RequestMethod.POST)
    public Pothole createPothole (@Valid @RequestBody Pothole pothole) {
        //Pothole newPothole = new Pothole(1, new BigDecimal(39.7484), new BigDecimal(-75.5444), "reported", "4-06-2024", "", "");

        //set up transfer to service -> dao -> jdbc
        //set up variable for service
        try{

            return potholeService.processPothole(pothole);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST));

        }



    }
}
