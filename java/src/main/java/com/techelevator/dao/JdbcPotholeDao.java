package com.techelevator.dao;

import com.techelevator.model.Pothole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //Stubbed out to avoid errors, not yet implemented
    @Override
    public List<Pothole> getPotholes (){
        List<Pothole> test = null;
        return test;
    }

    @Override
    public Pothole createPothole (Pothole newPothole){
        return newPothole;
    }

    //Map to DTO

}
