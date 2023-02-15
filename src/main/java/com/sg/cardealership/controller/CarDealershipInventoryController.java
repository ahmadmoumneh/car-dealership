/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;
import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.service.CarDealershipVehicleService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Car Dealers
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cardealership/inventory")
public class CarDealershipInventoryController implements CarDealershipVehicleType {
    @Autowired 
    private CarDealershipVehicleService vehicleService;
    
    @GetMapping("/new")
    public List<Vehicle> searchNew(
            @RequestParam String value,
            @RequestParam String minPrice,
            @RequestParam String maxPrice,
            @RequestParam String minYear,
            @RequestParam String maxYear,
            @RequestParam String userRole
    ) throws IOException, SQLException {
        InventoryQuery query = new InventoryQuery(value, minPrice, maxPrice,
        minYear, maxYear, userRole);
        
        return this.vehicleService.getVehicles(query, NEW);
    }
    
    @GetMapping("/used")
    public List<Vehicle> searchUsed(
            @RequestParam String value,
            @RequestParam String minPrice,
            @RequestParam String maxPrice,
            @RequestParam String minYear,
            @RequestParam String maxYear,
            @RequestParam String userRole
    ) throws IOException, SQLException {
        
        InventoryQuery query = new InventoryQuery(value, minPrice, maxPrice,
        minYear, maxYear, userRole);
        
        return this.vehicleService.getVehicles(query, USED);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<Vehicle> showVehicle(@PathVariable int id) throws SQLException, IOException {
        Vehicle vehicle = this.vehicleService.getVehicleById(id);
        
        Optional<ResponseEntity> response = 
                Optional.of(ResponseEntity.ok(vehicle));
       
        return response.orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }
    
}
