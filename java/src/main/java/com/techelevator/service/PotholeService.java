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

    public List<Pothole> getListOfDeletedPotholes(){
        return potholeDao.getListOfDeletedPotholes();
    }



    // ADDING NEW REFERENCE FOR UPDATING POTHOLE, FOLLOWING PATTERN FROM ABOVE

    public Pothole updatePothole(Pothole updatedPothole) {
        return potholeDao.updatePothole(updatedPothole);
    }

    // ADDING NEW REFERENCE FOR DELETING POTHOLE, FOLLOWING PATTERN FROM ABOVE

    public int deletePotholeById (int id) {
        return potholeDao.deletePotholeById(id);
    }


}
