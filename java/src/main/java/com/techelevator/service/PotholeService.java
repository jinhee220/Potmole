package com.techelevator.service;

import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;

public class PotholeService {

    private PotholeDao potholeDao;
    public PotholeService(PotholeDao potholeDao) {
        this.potholeDao = potholeDao;
    }

    public Pothole processPothole (Pothole newPothole){
        return newPothole;
    }
}
