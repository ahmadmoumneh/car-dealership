/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipVehicleDao;
import com.sg.cardealership.dao.VehicleRepository;
import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Vehicle;

import jakarta.persistence.EntityNotFoundException;

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

    @Autowired
    private VehicleRepository vehicleRepo;
    
    @Override 
    public List<Vehicle> getFeaturedVehicles(boolean isFeatured) throws IOException, SQLException {
        return this.vehicleRepo.findByIsFeatured(isFeatured);
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
        return vehicleRepo.findById(id).orElse(null);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws FileNotFoundException,
            IOException, SQLException {

        return this.vehicleRepo.save(vehicle);
    }
    
    @Override
    public boolean addPictureById(int id, byte[] picture) throws 
            IOException, SQLException {
        
        return this.vehicleDao.uploadPictureById(id, picture);
    }

    @Override
    public boolean deleteVehicleById(int id) throws IOException {
         try {
            vehicleRepo.deleteById(id);
         }
         catch(EntityNotFoundException e){
            System.out.println("vehicle id " + id + " do not exist");
            return false;
         }
         return true;
    }

    @Override
    public Vehicle editVehicle(Vehicle vehicle) throws SQLException {
        return this.vehicleRepo.save(vehicle);
    }
    
    @Override
    public boolean editPictureById(int id, byte[] picture) throws 
            IOException, SQLException {
        
        return this.vehicleDao.updatePictureById(id, picture);
    }

    @Override
    public Vehicle sellVehicleById(int id) throws IOException, SQLException {
        Vehicle vehicle = vehicleRepo.findById(id).orElse(null);
        vehicle.setIsSold(true);
        return vehicleRepo.save(vehicle);
    }
}
