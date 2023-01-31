/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Make.MakeMapper;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.User.UserMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Car Dealers
 */
@Repository
public class CarDealershipMakeDaoDB implements CarDealershipMakeDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Make addMake(Make make) throws FileNotFoundException, 
            IOException, SQLException {
        
        final String insertMake = 
                "INSERT INTO make(makeName, userId, makeDate) VALUES(?,?,?)";
        
        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = 
                    conn.prepareStatement(insertMake, 
                            Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, make.getMakeName());
            statement.setInt(2, make.getMakeUser().getUserId());
            statement.setDate(3, Date.valueOf(make.getMakeDate()));
            
            return statement;
        }, kh);
        
        Number key = kh.getKey();
        
        if (key != null)
            make.setMakeId(key.intValue());
        
        else
            throw new SQLException("Make ID was null.");
        
        return make;
    }
    
    private void addUserForMake(Make make) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN make m ON u.userId = m.userId WHERE m.makeId = ?";
        User user = jdbc.queryForObject(sql, new UserMapper(), 
                make.getMakeId());
        
        make.setMakeUser(user);
    }
    
    private void addUsersForMakes(List<Make> makes) {
        for (Make make : makes) {
            addUserForMake(make);
        }
    }
    
    private void addUserToMake(Make make) {
        addUserForMake(make);
    }
    
    @Override
    public List<Make> getAllMakes() {
        final String sql = "SELECT * FROM make";
        List<Make> makes = jdbc.query(sql, new Make.MakeMapper());
        addUsersForMakes(makes);
        return makes;
    }
    
    @Override
    @Transactional
    public boolean deleteMakeById(int id) { 
        final String DELETE_MAKE = "DELETE FROM make WHERE makeId = ?";
        return jdbc.update(DELETE_MAKE, id) > 0;
    }

    @Override
    public Make getMakeById(int id) {
        try {
            final String sql = "SELECT * FROM make WHERE makeId = ?";
            Make make = jdbc.queryForObject(sql, new MakeMapper(), id);
            addUserToMake(make);
            return make;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public boolean resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE make AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }
}
