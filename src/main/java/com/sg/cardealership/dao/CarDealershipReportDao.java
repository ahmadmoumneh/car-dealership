/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReportQuery;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipReportDao {
    SalesReport generateSalesReport(SalesReportQuery query);
    
    List<InventoryReport> generateInventoryReport(String vehicleType);
}