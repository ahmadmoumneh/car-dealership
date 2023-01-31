/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.InventoryReport.InventoryReportMapper;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReport.SalesReportMapper;
import com.sg.cardealership.dto.SalesReportQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Car Dealers
 */
@Repository
public class CarDealershipReportDaoDB implements CarDealershipReportDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public SalesReport generateSalesReport(SalesReportQuery query) {
        try {
            return jdbc.queryForObject(query.getSql(), 
                    new SalesReportMapper(), query.args());
        }
        catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public InventoryReport generateInventoryReport(String vehicleType) {
        InventoryReport inventoryReport = new InventoryReport(vehicleType);
        
        return jdbc.queryForObject(inventoryReport.getSql(), 
                new InventoryReportMapper(), inventoryReport.getArgs());
    }
}
