/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.ModelRepository;
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
    private ModelRepository modelRepo;
    
    @Override
    public Model addModel(Model model) throws IOException, FileNotFoundException, SQLException {
        return modelRepo.save(model);
    }

    @Override
    public List<Model> getAllModels() {
         return modelRepo.findAll();
    }

    @Override
    public Model getModelById(int id) {
        return modelRepo.findById(id).orElse(null);
    }
}