/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;

import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.service.CarDealershipPurchaseService;
import com.sg.cardealership.service.CarDealershipVehicleService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/cardealership/sales")
public class CarDealershipSalesController {
    @Autowired private CarDealershipVehicleService vehicleService;
    @Autowired private CarDealershipPurchaseService purchaseService;
    
    @GetMapping
    public List<Vehicle> searchSales(
            @RequestParam String value,
            @RequestParam String minPrice,
            @RequestParam String maxPrice,
            @RequestParam String minYear,
            @RequestParam String maxYear
    ) 
            throws IOException, SQLException {
        InventoryQuery query = new InventoryQuery(value, minPrice, maxPrice,
        minYear, maxYear, "Sales");
        
        return this.vehicleService.getVehicles(query);
    }
    
    @PostMapping("/{id}")
    public Purchase logPurchase(@RequestBody Purchase purchase, 
            @PathVariable int id) throws Exception {
        
        Vehicle vehicle = vehicleService.getVehicleById(id);
                
        purchaseService.logPurchase(purchase, vehicle);
        vehicleService.sellVehicleById(id);
                
        return purchase;
    }
    
}
