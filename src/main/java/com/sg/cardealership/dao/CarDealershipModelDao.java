/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipModelDao {
    Model addModel(Model model) throws 
            FileNotFoundException, 
            IOException, 
            SQLException;
    
    List<Model> getAllModels();
    
    Model getModelById(int id);
    
    boolean deleteModelById(int id);
    
    boolean resetAutoIncrement(int startId);
}
