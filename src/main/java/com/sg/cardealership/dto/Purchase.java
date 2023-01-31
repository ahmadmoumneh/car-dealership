/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Car Dealers
 */
public class Purchase {
    private int purchaseId;
    private String name;
    private String phone;
    private String email;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private BigDecimal price;
    private String purchaseType;
    private LocalDate date;
    private User salesUser;
    
    public Purchase() {}

    public Purchase(String name, String phone, String email, 
            String street1, String street2, String city, String state, 
            String zipcode, BigDecimal price, String purchaseType, LocalDate date, 
            User salesUser) {
        
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.price = price;
        this.purchaseType = purchaseType;
        this.date = date;
        this.salesUser = salesUser;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getSalesUser() {
        return salesUser;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSalesUser(User salesUser) {
        this.salesUser = salesUser;
    }
    
    public final static class PurchaseMapper implements RowMapper<Purchase> {
        @Override
        public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
            Purchase purchase = new Purchase();
            purchase.setPurchaseId(rs.getInt("purchaseId"));
            purchase.setName(rs.getString("name"));
            purchase.setPhone(rs.getString("phone"));
            purchase.setEmail(rs.getString("email"));
            purchase.setStreet1(rs.getString("street1"));
            purchase.setStreet2(rs.getString("street2"));
            purchase.setCity(rs.getString("city"));
            purchase.setState(rs.getString("state"));
            purchase.setZipcode(rs.getString("zipcode"));
            purchase.setPrice(rs.getBigDecimal("price"));
            purchase.setPurchaseType(
                    rs.getString("purchaseType"));
            purchase.setDate(rs.getDate("date").toLocalDate());
            
            return purchase;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.purchaseId;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.phone);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.street1);
        hash = 59 * hash + Objects.hashCode(this.street2);
        hash = 59 * hash + Objects.hashCode(this.city);
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.zipcode);
        hash = 59 * hash + Objects.hashCode(this.price);
        hash = 59 * hash + Objects.hashCode(this.purchaseType);
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.salesUser);
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
        final Purchase other = (Purchase) obj;
        if (this.purchaseId != other.purchaseId) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.street1, other.street1)) {
            return false;
        }
        if (!Objects.equals(this.street2, other.street2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.purchaseType, other.purchaseType)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.salesUser, other.salesUser);
    }

    @Override
    public String toString() {
        return "Purchase{" + "purchaseId=" + purchaseId + ", name=" + name + ", phone=" + 
                phone + ", email=" + email + ", street1=" + street1 + 
                ", street2=" + street2 + ", city=" + city + ", state=" + 
                state + ", zipcode=" + zipcode + ", price=" + price + 
                ", purchaseType=" + purchaseType + ", date=" + date + 
                ", salesUser=" + salesUser + '}';
    }
}
