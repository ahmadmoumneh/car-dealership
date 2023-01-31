/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Make;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipMakeDao {
    Make addMake(Make make) throws 
            FileNotFoundException, 
            IOException, 
            SQLException;
    
    List<Make> getAllMakes();
    
    Make getMakeById(int id);
    
    boolean deleteMakeById(int id);
    
    boolean resetAutoIncrement(int startId);
}
