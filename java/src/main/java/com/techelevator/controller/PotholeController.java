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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        Pothole newPothole = new Pothole();
        return newPothole;

    }
}
