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
public class SalesReport {
    private final int id;
    private String userName;
    private BigDecimal totalSales;
    private int totalVehicles;
    
    private static int ids;
    
    public SalesReport(){
        this.id = ids++;
    }

    public SalesReport(String userName, BigDecimal totalSales, 
            int totalVehicles) {
        this.id = ids++;
        this.userName = userName;
        this.totalSales = totalSales;
        this.totalVehicles = totalVehicles;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public int getTotalVehicles() {
        return totalVehicles;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotalVehicles(int totalVehicles) {
        this.totalVehicles = totalVehicles;
    }
    
    public final static class SalesReportMapper implements RowMapper<SalesReport> {
        @Override
        public SalesReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            SalesReport salesReport = new SalesReport();
            salesReport.setUserName(rs.getString("name"));
            salesReport.setTotalSales(
                    rs.getBigDecimal("totalSales"));
            salesReport.setTotalVehicles(
                    rs.getInt("totalVehicles"));
            
            return salesReport;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.userName);
        hash = 79 * hash + Objects.hashCode(this.totalSales);
        hash = 79 * hash + this.totalVehicles;
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
        final SalesReport other = (SalesReport) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.totalVehicles != other.totalVehicles) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return Objects.equals(this.totalSales, other.totalSales);
    }

    @Override
    public String toString() {
        return "SalesReport{" + "id=" + id + ", userName=" + userName + 
                ", totalSales=" + totalSales + ", totalVehicles=" + 
                totalVehicles + '}';
    }
    
    
}
