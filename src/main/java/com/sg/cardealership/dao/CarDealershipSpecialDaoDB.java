/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Special;
import com.sg.cardealership.dto.Special.SpecialMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Car Dealers
 */
@Repository
public class CarDealershipSpecialDaoDB implements CarDealershipSpecialDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public List<Special> getAllSpecials() {
        final String SELECT_ALL_SPECIALS = "SELECT * FROM special";
        return jdbc.query(SELECT_ALL_SPECIALS, new SpecialMapper()); 
    }

    @Override
    @Transactional
    public Special addSpecial(Special special) {
        final String INSERT_SPECIAL = "INSERT INTO special(title, description) " + "VALUES(?,?)";
        jdbc.update(INSERT_SPECIAL, 
                special.getTitle(),
                special.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);
        return special;   
    }
    

    @Override
    public boolean deleteSpecialById(int id) {
        final String DELETE_SPECIAL = "DELETE FROM special WHERE specialId = ?";
        return jdbc.update(DELETE_SPECIAL, id) > 0;
    }

    @Override
    public Special getSpecialById(int id) {
        try {
            final String SELECT_SPECIAL_BY_ID = "SELECT * FROM special WHERE specialId = ?";
            return jdbc.queryForObject(SELECT_SPECIAL_BY_ID, new SpecialMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE contact AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }
}
