/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

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
public interface CarDealershipVehicleDao {
    List<Vehicle> getVehicles(boolean isFeatured) throws
            IOException, SQLException;
    
    List<Vehicle> getVehicles(InventoryQuery query) throws
            IOException, SQLException;
    
    List<Vehicle> getVehicles(InventoryQuery query, String vehicleType) throws
            IOException, SQLException;
    
    Vehicle getVehicleById(int id) throws IOException, SQLException;
    
    byte[] getPictureById(int id) throws SQLException;
    
    Vehicle addVehicle(Vehicle vehicle) throws
            FileNotFoundException, 
            IOException, 
            SQLException;
    
    boolean deleteVehicleById(int id) throws IOException;
    
    boolean deletePictureById(int id) throws IOException;
    
    boolean updateVehicle(Vehicle vehicle)
            throws SQLException;
    
    boolean uploadPictureById(int id, byte[] picture) throws 
            IOException, SQLException;
    
    boolean updatePictureById(int id, byte[] picture) throws 
            IOException, SQLException;
    
    boolean resetVehicleAutoIncrement(int startId);
    
    boolean resetPictureAutoIncrement(int startId);
}
