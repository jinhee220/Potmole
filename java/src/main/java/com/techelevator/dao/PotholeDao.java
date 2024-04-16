package com.techelevator.dao;
import com.techelevator.model.Pothole;

import java.math.BigDecimal;
import java.util.List;

public interface PotholeDao {
    Pothole getPotholeById (int id);

    Pothole createPothole (Pothole newPothole);

    List<Pothole> getListOfPotholes();

    List<Pothole> getListOfDeletedPotholes();

    Pothole updatePothole (Pothole updatedPothole);

    Pothole getPotholeIdByLatLon (BigDecimal lat, BigDecimal lon);

    int deletePotholeById (int id);


}
