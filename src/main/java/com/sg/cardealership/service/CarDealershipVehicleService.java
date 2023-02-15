/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Vehicle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipVehicleService {
    List<Vehicle> getFeaturedVehicles(boolean isFeatured) throws 
            IOException, SQLException;
    
    List<Vehicle> getVehicles(InventoryQuery query, String type) throws
            IOException, SQLException;
    
    List<Vehicle> getVehicles(InventoryQuery query) throws
            IOException, SQLException;
    
    Vehicle getVehicleById(int id) throws IOException, SQLException;
    
    Vehicle addVehicle(Vehicle vehicle) throws 
            FileNotFoundException,
            IOException, 
            SQLException;
    
    boolean addPictureById(int id, byte[] picture) throws IOException, 
            SQLException;
    
    boolean deleteVehicleById(int id) throws IOException;
    
    Vehicle editVehicle(Vehicle vehicle) throws
            SQLException;
    
    boolean editPictureById(int id, byte[] picture) throws
            IOException, SQLException;
    
    Vehicle sellVehicleById(int id) throws IOException, SQLException;
}
