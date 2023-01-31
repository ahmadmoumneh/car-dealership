/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import com.sg.cardealership.common.CarDealershipVehicleType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Car Dealers
 */
public class Vehicle implements CarDealershipVehicleType {
    private int vehicleId;
    private String vehicleType;
    private Model model;
    private int year;
    private String bodyStyle;
    private String transmission;
    private String color;
    private String interior;
    private int mileage;
    private String vin; 
    private BigDecimal salePrice;
    private BigDecimal msrp;
    private String description;
    private boolean isSold;
    private boolean isFeatured;
    
    public Vehicle(){}

    public Vehicle(Model model, int year, 
            String bodyStyle, String transmission, String color, 
            String interior, int mileage, String vin, BigDecimal
            salePrice, BigDecimal msrp, String description) {
        
        this.model = model;
        this.year = year;
        this.bodyStyle = bodyStyle;
        this.transmission = transmission;
        this.color = color;
        this.interior = interior;
        this.mileage = mileage;
        this.vin = vin;
        this.salePrice = salePrice;
        this.msrp = msrp;
        this.description = description;
        
        this.setVehicleType();
    }
    
    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Model getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }

    public String getInterior() {
        return interior;
    }

    public int getMileage() {
        return mileage;
    }

    public String getVin() {
        return vin;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean isSold() {
        return isSold;
    }
    
    public boolean isFeatured() {
        return isFeatured;
    }

    public void setVehicleId(int id) {
        this.vehicleId = id;
    }
    
    public final void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public final void setVehicleType() {
        if (this.mileage < 1000)
            this.vehicleType = NEW;
        
        else
            this.vehicleType = USED;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
        
        this.setVehicleType();
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }
    
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
    
    public final static class VehicleMapper implements RowMapper<Vehicle> {
        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(rs.getInt("vehicleId"));
            vehicle.setVehicleType(rs.getString("vehicleType"));
            vehicle.setYear(rs.getInt("year"));
            vehicle.setBodyStyle(rs.getString("bodyStyle"));
            vehicle.setTransmission(rs.getString("transmission"));
            vehicle.setColor(rs.getString("color"));
            vehicle.setInterior(rs.getString("interior"));
            vehicle.setMileage(rs.getInt("mileage"));
            vehicle.setVin(rs.getString("vin"));
            vehicle.setSalePrice(rs.getBigDecimal("salePrice"));
            vehicle.setMsrp(rs.getBigDecimal("msrp"));
            vehicle.setDescription(rs.getString("description"));
            vehicle.setIsSold(rs.getBoolean("isSold"));
            vehicle.setIsFeatured(rs.getBoolean("isFeatured"));
            
            return vehicle;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.vehicleId;
        hash = 89 * hash + Objects.hashCode(this.vehicleType);
        hash = 89 * hash + Objects.hashCode(this.model);
        hash = 89 * hash + this.year;
        hash = 89 * hash + Objects.hashCode(this.salePrice);
        hash = 89 * hash + Objects.hashCode(this.bodyStyle);
        hash = 89 * hash + Objects.hashCode(this.transmission);
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + Objects.hashCode(this.interior);
        hash = 89 * hash + this.mileage;
        hash = 89 * hash + Objects.hashCode(this.vin);
        hash = 89 * hash + Objects.hashCode(this.msrp);
        hash = 89 * hash + Objects.hashCode(this.description);
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
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (!Objects.equals(this.vehicleType, other.vehicleType)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.salePrice, other.salePrice)) {
            return false;
        }
        return Objects.equals(this.msrp, other.msrp);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + vehicleId + ", vehicleType=" + vehicleType + 
                 ", model=" + model + ", year=" + year + 
                ", salePrice=" + salePrice + ", bodyStyle=" + bodyStyle + 
                ", transmission=" + transmission + ", color=" + color + 
                ", interior=" + interior + ", mileage=" + mileage + ", vin=" + 
                vin + ", msrp=" + msrp + ", description=" + description 
                + ", isSold=" + isSold + ", isFeatured=" 
                + isFeatured + '}';
    }
}
