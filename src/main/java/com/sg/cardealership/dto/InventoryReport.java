/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Car Dealers
 */
public class InventoryReport {
    private int id;
    private int year;
    private String make;
    private String model;
    private int count;
    private BigDecimal stockValue;
    private String sql;
    private static int ids;
    private Object args;
    
    public InventoryReport(){
        this.id = ids++;
    }
    
    public InventoryReport(String vehicleType){
        this.generateSql(vehicleType);
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public String getSql() {
        return sql;
    }

    public Object getArgs() {
        return args;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public final void generateSql(String vehicleType) {
        this.sql = "SELECT v.year AS year, mk.makeName AS make, md.modelName "
                + "AS model, COUNT(v.vehicleId) AS count, SUM(v.msrp) AS "
                + "stockValue FROM vehicle v JOIN model md ON v.modelId = "
                + "md.modelId JOIN make mk ON md.makeId = mk.makeId WHERE "
                + "v.vehicleType = ? GROUP BY year, make, model";
        
        this.args = vehicleType;
    }
    
    public final static class InventoryReportMapper implements 
            RowMapper<InventoryReport> {
        
        @Override
        public InventoryReport mapRow(ResultSet rs, int rowNum) 
                throws SQLException {
            InventoryReport inventoryReport = new InventoryReport();
            
            inventoryReport.setYear(rs.getInt("year"));
            inventoryReport.setMake(rs.getString("make"));
            inventoryReport.setModel(rs.getString("model"));
            inventoryReport.setCount(rs.getInt("count"));
            inventoryReport.setStockValue(
                    rs.getBigDecimal("stockValue"));
            
            return inventoryReport;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.year;
        hash = 29 * hash + Objects.hashCode(this.make);
        hash = 29 * hash + Objects.hashCode(this.model);
        hash = 29 * hash + this.count;
        hash = 29 * hash + Objects.hashCode(this.stockValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InventoryReport other = (InventoryReport) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        return Objects.equals(this.stockValue, other.stockValue);
    }

    @Override
    public String toString() {
        return "InventoryReport{" + "id=" + id +  ", year=" + year + ", make=" 
                + make + ", model=" + model + ", count=" + count + 
                ", stockValue=" + stockValue + '}';
    }
}
