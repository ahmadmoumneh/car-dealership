/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.dao.CarDealershipReportDao;
import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReportQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipReportServiceImpl implements 
        CarDealershipReportService,
        CarDealershipVehicleType {
    
    @Autowired CarDealershipReportDao reportDao;
    
    @Override
    public SalesReport getSalesReport(SalesReportQuery query) {
        return this.reportDao.generateSalesReport(query);
    }

    @Override
    public InventoryReport getNewVehiclesReport() {
        return this.reportDao.generateInventoryReport(NEW);
    }

    @Override
    public InventoryReport getUsedVehiclesReport() {
        return this.reportDao.generateInventoryReport(USED);
    }
}
