/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Car Dealers
 */
public class InventoryQuery {
    private int id;
    private String value;
    private String minPrice;
    private String maxPrice;
    private String minYear;
    private String maxYear;
    private String userRole;
    private String sql;

    private static int ids;
    
    public static final String NO_MAX_PRICE = "1000000";
    public static final String NO_MIN_PRICE = "0";
    public static final String NO_MIN_YEAR = "0";
    public static final String NO_MAX_YEAR = "2023";
    
    public InventoryQuery(String value, String minPrice, String maxPrice, String minYear, String maxYear, String userRole) {
        this.id = ids++;
        this.value = value;
        this.minPrice = minPrice.isEmpty()? NO_MIN_PRICE : minPrice;
        this.maxPrice = maxPrice.isEmpty()? NO_MAX_PRICE : maxPrice;
        this.minYear = minYear.isEmpty()? NO_MIN_YEAR : minYear;
        this.maxYear = maxYear.isEmpty()? NO_MAX_YEAR : maxYear;
        this.userRole = userRole;
        this.generateSql();
    }

    public int getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }

    public String getSql() {
        return sql;
    }
    
    public final void generateSql() {
        boolean isValueNumeric = isNumeric(this.value);
        
        this.sql = "SELECT v.* FROM vehicle v JOIN model md ON v.modelId = "
                + "md.modelId JOIN make mk ON md.makeId = mk.makeId WHERE " + 
                (isValueNumeric?  "year = " + this.value : 
                ("(md.modelName LIKE '%" + this.value + "%' OR mk.makeName LIKE '%" + 
                this.value + "%')")) + " AND (" + 
                "salePrice BETWEEN " + this.minPrice + 
                " AND " + this.maxPrice + ")" + (!isValueNumeric? 
                (" AND (year BETWEEN " + this.minYear + " AND " 
                + this.maxYear + ")") : "") + (this.userRole.equals("Admin")? 
                " AND isSold = 0" : "");
    }
    
    public void appendSql(String sql) {
        this.sql += sql;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getMinYear() {
        return minYear;
    }

    public String getMaxYear() {
        return maxYear;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinYear(String minYear) {
        this.minYear = minYear;
    }

    public void setMaxYear(String maxYear) {
        this.maxYear = maxYear;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    private boolean isNumeric(String value) {
        try {
            Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.value);
        hash = 67 * hash + Objects.hashCode(this.minPrice);
        hash = 67 * hash + Objects.hashCode(this.maxPrice);
        hash = 67 * hash + Objects.hashCode(this.minYear);
        hash = 67 * hash + Objects.hashCode(this.maxYear);
        hash = 67 * hash + Objects.hashCode(this.userRole);
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
        final InventoryQuery other = (InventoryQuery) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.minPrice, other.minPrice)) {
            return false;
        }
        if (!Objects.equals(this.maxPrice, other.maxPrice)) {
            return false;
        }
        if (!Objects.equals(this.minYear, other.minYear)) {
            return false;
        }
        if (!Objects.equals(this.maxYear, other.maxYear)) {
            return false;
        }
        return Objects.equals(this.userRole, other.userRole);
    }

    @Override
    public String toString() {
        return "InventoryQuery{" + "id=" + id + ", value=" + value + 
                ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + 
                ", minYear=" + minYear + ", maxYear=" + maxYear + 
                ", userRole=" + userRole + '}';
    }

    
}
