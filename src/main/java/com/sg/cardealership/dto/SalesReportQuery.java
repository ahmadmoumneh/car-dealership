/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import static com.sg.cardealership.common.CarDealershipDateFormatter.DATE_FORMATTER;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Car Dealers
 */
public class SalesReportQuery {
    private final int id;
    private List<User> users;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String sql;
    private Object[] args;
    
    private static int ids;
    
    public SalesReportQuery() {
        this.id = ids++;
    }
    
    public SalesReportQuery(List<User> users, LocalDate fromDate, LocalDate toDate) {
        this.id = ids++;
        this.users = users;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.generateSql();      
    }

    public int getId() {
        return id;
    }
    
    public List<User> getFilter() {
        return users;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
    
    public final void generateSql() {
        String startDate = this.fromDate.format(DATE_FORMATTER);
        String endDate = this.toDate.format(DATE_FORMATTER);
        
        List<String> argsList = this.users.stream().map((user) ->  user.getFirstName() 
                + " " + user.getLastName())
                .collect(Collectors.toList());
        
        this.sql = "SELECT CONCAT(u.firstName, ' ', u.lastName) AS name, "
                + "totalSales, totalVehicles FROM user u JOIN "
                + "(SELECT userId, SUM(price) AS totalSales, COUNT(purchaseId) "
                + "AS totalVehicles FROM purchase WHERE date BETWEEN "
                + "? AND ? GROUP BY userId) AS r ON u.userId = r.userId " 
                + "WHERE CONCAT(u.firstName, ' ', u.lastName) "
                + "IN (" + placeholders(argsList) + ")";
        
        argsList.add(0, endDate);
        argsList.add(0, startDate);
        
        this.args = argsList.toArray();
    }
    
    private String placeholders(List<String> list) {
        return list.stream().map(item -> "?")
                .collect(Collectors.joining(","));
    }

    public String getSql() {
        return sql;
    }
    
    public Object[] args() {
        return args;
    }
    
    public void setFilter(List<User> users) {
        this.users = users;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.users);
        hash = 83 * hash + Objects.hashCode(this.fromDate);
        hash = 83 * hash + Objects.hashCode(this.toDate);
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
        final SalesReportQuery other = (SalesReportQuery) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        if (!Objects.equals(this.fromDate, other.fromDate)) {
            return false;
        }
        return Objects.equals(this.toDate, other.toDate);
    }

    @Override
    public String toString() {
        return "SalesReportQuery{" + "id=" + id + 
                ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
    }
}
