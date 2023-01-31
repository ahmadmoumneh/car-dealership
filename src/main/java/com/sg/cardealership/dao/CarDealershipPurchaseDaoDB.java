/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.Purchase.PurchaseMapper;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.User.UserMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Car Dealers
 */
@Repository
public class CarDealershipPurchaseDaoDB implements CarDealershipPurchaseDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public Purchase addPurchase(Purchase purchase) {
        
        final String ADD_PURCHASE = "INSERT INTO purchase(name, phone, " +
        "email, street1, street2, city, state, zipcode, price, purchaseType, " +
            "date, userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(ADD_PURCHASE);
                    
            statement.setString(1,purchase.getName());
            statement.setString(2,purchase.getPhone());
            statement.setString(3,purchase.getEmail());
            statement.setString(4,purchase.getStreet1());
            statement.setString(5,purchase.getStreet2());
            statement.setString(6,purchase.getCity());
            statement.setString(7,purchase.getState());
            statement.setString(8,purchase.getZipcode());
            statement.setBigDecimal(9,purchase.getPrice());
            statement.setString(10,purchase.getPurchaseType());
            statement.setDate(11,Date.valueOf(purchase.getDate()));
            statement.setInt(12, purchase.getSalesUser().getUserId());
            return statement;
        });
        
        int purchaseId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", 
                Integer.class);
        
        purchase.setPurchaseId(purchaseId);
        
      return purchase;
    }
    
    @Override
    public boolean resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE purchase AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }

    @Override
    public Purchase getPurchaseById(int id) {
        try {
            final String sql = "SELECT * FROM purchase WHERE purchaseId = ?;";

            Purchase purchase = jdbc.queryForObject(sql, new PurchaseMapper(), 
                    id);

            addUserToPurchase(purchase);
            return purchase;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    private void addUserToPurchase(Purchase purchase) {
        purchase.setSalesUser(getUserForPurchase(purchase));
    }
    
    private User getUserForPurchase(Purchase purchase) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN purchase p ON u.userId = p.userId "
                + "WHERE p.purchaseId = ?";
        
        return jdbc.queryForObject(sql, new UserMapper(), 
                purchase.getPurchaseId());
    }

    @Override
    public boolean deletePurchaseById(int id) {
        final String DELETE_PURCHASE = "DELETE FROM purchase WHERE "
                + "purchaseId = ?";
        return jdbc.update(DELETE_PURCHASE, id) > 0;
    }
    
    @Override
    public List<Purchase> getAllPurchases() {
        final String DELETE_PURCHASE = "SELECT * FROM purchase";
        List<Purchase> purchases = jdbc.query(DELETE_PURCHASE, new PurchaseMapper());
        addUsersToPurchases(purchases);
        return purchases;
    }
    
    private void addUsersToPurchases(List<Purchase> purchases) {
        for (Purchase purchase: purchases)
            addUserToPurchase(purchase);
    }

    @Override
    public boolean deleteAllPurchases() {
        final String DELETE_PURCHASE = "DELETE FROM purchase";
        return jdbc.update(DELETE_PURCHASE) > 0;
    }
    
}
