package com.techelevator.service;

import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PotholeService {

    private PotholeDao potholeDao;
    public PotholeService(PotholeDao potholeDao) {
        this.potholeDao = potholeDao;
    }

    public Pothole processPothole (Pothole newPothole){
        return potholeDao.createPothole(newPothole);
    }

    public List<Pothole> getListOfPotholes(){
        return potholeDao.getListOfPotholes();
    }
}
