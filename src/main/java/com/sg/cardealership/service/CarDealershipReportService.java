/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReportQuery;


/**
 *
 * @author Car Dealers
 */
public interface CarDealershipReportService {

    SalesReport getSalesReport(SalesReportQuery query);
    
    InventoryReport getNewVehiclesReport();
    
    InventoryReport getUsedVehiclesReport();
}
