/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipModelDao;
import com.sg.cardealership.dto.Model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipModelServiceImpl implements 
      CarDealershipModelService {
    
    @Autowired
    private CarDealershipModelDao modelDao;
    
    @Override
    public Model addModel(Model model) throws IOException, FileNotFoundException, SQLException {
        return modelDao.addModel(model);
    }

    @Override
    public List<Model> getAllModels() {
         return modelDao.getAllModels();
    }

    @Override
    public Model getModelById(int id) {
        return modelDao.getModelById(id);
    }
}