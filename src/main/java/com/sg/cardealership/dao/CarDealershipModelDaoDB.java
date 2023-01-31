/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.Model.ModelMapper;
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
public class CarDealershipModelDaoDB implements CarDealershipModelDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Model addModel(Model model) throws FileNotFoundException, 
            IOException, SQLException {
        
        final String insertModel = 
                "INSERT INTO model(modelName, makeId, userId, modelDate) "
                + "VALUES (?,?,?,?)";
        
        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = 
                    conn.prepareStatement(insertModel, 
                            Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, model.getModelName());
            statement.setInt(2, model.getModelMake().getMakeId());
            statement.setInt(3, model.getModelUser().getUserId());
            statement.setDate(4, Date.valueOf(model.getModelDate()));
            
            return statement;
        }, kh);
        
        Number key = kh.getKey();
        
        if (key != null)
            model.setModelId(key.intValue());
        
        else
            throw new SQLException("Model ID was null.");
                
        return model;
    }
    
    @Override
    public List<Model> getAllModels() {
        final String sql = "SELECT * FROM model;";
        List<Model> models = jdbc.query(sql, new ModelMapper());
        
        addMakesAndUsersToModels(models);
        
        return models;
    }
    
    private void addUserForMake(Make make) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN make m ON u.userId = m.userId WHERE m.makeId = ?";
        User user = jdbc.queryForObject(sql, new UserMapper(), 
                make.getMakeId());
        
        make.setMakeUser(user);
    }
    
    private void addMakeForModel(Model model) {
        final String sql = "SELECT mk.* FROM make mk "
                + "JOIN model md ON mk.makeId = md.makeId "
                 + "WHERE md.modelId = ?";
          
        Make make = jdbc.queryForObject(sql, new Make.MakeMapper(), 
                model.getModelId());
        
        addUserForMake(make);
        model.setModelMake(make);
    }
    
    private void addMakesAndUsersToModels(List<Model> models) {
        for (Model model : models) {
            addMakeForModel(model);
            addUserForModel(model);
        }
    }
    
    private void addUserForModel(Model model) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN model m ON u.userId = m.userId WHERE m.modelId = ?";
        
        User user = jdbc.queryForObject(sql, new UserMapper(), 
                model.getModelId());
        
        model.setModelUser(user);
    }

    @Override
    public Model getModelById(int id) {
        try {
            final String sql = "SELECT * FROM model WHERE modelId = ?;";
            Model model = jdbc.queryForObject(sql, new ModelMapper(), id);

            addMakeForModel(model);
            addUserForModel(model);
            return model;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean deleteModelById(int id) {
        final String DELETE_MODEL = "DELETE FROM model WHERE modelId = ?";
        return jdbc.update(DELETE_MODEL, id) > 0;
    }

    @Override
    public boolean resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE model AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }
}
