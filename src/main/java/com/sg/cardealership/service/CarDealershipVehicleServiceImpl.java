/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipVehicleDao;
import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Vehicle;
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
public class CarDealershipVehicleServiceImpl implements
        CarDealershipVehicleService{
    
    @Autowired
    private CarDealershipVehicleDao vehicleDao;
    
    @Override 
    public List<Vehicle> getVehicles(boolean isFeatured) throws IOException, SQLException {
        return this.vehicleDao.getVehicles(isFeatured);
    }

    @Override
    public List<Vehicle> getVehicles(InventoryQuery query, String type) throws IOException, SQLException {
        return this.vehicleDao.getVehicles(query, type);
    }
    
    @Override
    public List<Vehicle> getVehicles(InventoryQuery query) throws IOException, SQLException {
        return this.vehicleDao.getVehicles(query);
    }

    @Override
    public Vehicle getVehicleById(int id) throws IOException, SQLException {
        return this.vehicleDao.getVehicleById(id);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws FileNotFoundException,
            IOException, SQLException {
        return this.vehicleDao.addVehicle(vehicle);
    }
    
    @Override
    public boolean addPictureById(int id, byte[] picture) throws 
            IOException, SQLException {
        
        return this.vehicleDao.uploadPictureById(id, picture);
    }

    @Override
    public boolean deleteVehicleById(int id) throws IOException {
        return this.vehicleDao.deleteVehicleById(id);
    }

    @Override
    public boolean editVehicle(Vehicle vehicle) throws SQLException {
        return this.vehicleDao.updateVehicle(vehicle);
    }
    
    @Override
    public boolean editPictureById(int id, byte[] picture) throws 
            IOException, SQLException {
        
        return this.vehicleDao.updatePictureById(id, picture);
    }

    @Override
    public boolean sellVehicleById(int id) throws IOException, SQLException {
        Vehicle vehicle = this.vehicleDao.getVehicleById(id);
        vehicle.setIsSold(true);
        return this.vehicleDao.updateVehicle(vehicle);
    }
}
