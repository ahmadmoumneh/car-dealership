/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;

import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReportQuery;
import com.sg.cardealership.service.CarDealershipReportService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Car Dealers
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cardealership/reports")
public class CarDealershipReportsController {
    @Autowired
    private CarDealershipReportService reportService;
    
    @GetMapping("/sales")
    public ResponseEntity<SalesReport> getSalesReport(SalesReportQuery query) {
        SalesReport salesReport = 
                this.reportService.getSalesReport(query);
        
        Optional<ResponseEntity> response = 
                Optional.of(ResponseEntity.ok(salesReport));
       
        return response.orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/inventory/new")
    public ResponseEntity<InventoryReport> getNewVehiclesReport() {
        InventoryReport inventoryReport = this.reportService.getNewVehiclesReport();
        
        Optional<ResponseEntity> response = 
                Optional.of(ResponseEntity.ok(inventoryReport));
       
        return response.orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/inventory/used")
    public ResponseEntity<InventoryReport> getUsedVehiclesReport() {
        InventoryReport inventoryReport = this.reportService.getUsedVehiclesReport();
        
        Optional<ResponseEntity> response = 
                Optional.of(ResponseEntity.ok(inventoryReport));
       
        return response.orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }
}
