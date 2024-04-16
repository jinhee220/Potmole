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
import java.util.List;

@RestController
@CrossOrigin
public class PotholeController {

    private PotholeService potholeService;

    public PotholeController(PotholeService potholeService) {
        this.potholeService = potholeService;
    }

    // Request Mappings


    @RequestMapping(path = "/getAllPotholes", method = RequestMethod.GET)
    public List<Pothole> getPotholes () {
        List<Pothole> allPotholes ;
        try {
            allPotholes = potholeService.getListOfPotholes();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (allPotholes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users Found");
        }
        return allPotholes;
    }

    @RequestMapping(path = "/getAllDeletedPotholes", method = RequestMethod.GET)
    public List<Pothole> getDeletedPotholes () {
        List<Pothole> allPotholes ;
        try {
            allPotholes = potholeService.getListOfDeletedPotholes();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (allPotholes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users Found");
        }
        return allPotholes;
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

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }

    }

    // ADDING NEW METHOD FOR UPDATING POTHOLE, FOLLOWING PATTERN FROM CREATE POTHOLE ABOVE

    @RequestMapping(path = "/updatePothole", method = RequestMethod.PUT)
    public Pothole updatePothole (@Valid @RequestBody Pothole updatedPothole) {

        try {

            return potholeService.updatePothole(updatedPothole);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }

    }


    // ADDING NEW METHOD FOR DELETING POTHOLE, FOLLOWING PATTERN FROM CREATE POTHOLE ABOVE

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deletePothole/{id}", method = RequestMethod.DELETE)
    public int deletePotholeById (@PathVariable int id) {

        try {

            return potholeService.deletePotholeById(id);

        } catch (DaoException e) {

            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), e.getMessage());

        }
    }

}
