/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipPurchaseDao;
import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.Vehicle;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipPurchaseServiceImpl implements 
        CarDealershipPurchaseService {
    
    @Autowired
    private CarDealershipPurchaseDao purchaseDao;
    
    @Override
    public List<Purchase> getAllPurchases() {
         return purchaseDao.getAllPurchases();
    }
    
    @Override
    public Purchase logPurchase(Purchase purchase, 
            Vehicle vehicle) throws Exception {
        
        if(purchase.getPrice().compareTo(
                vehicle.getSalePrice().multiply(new BigDecimal("0.95"))) == -1) {
            
            throw new Exception(
                    "Purchase price cannot be less than Sales price.");
        }
        
        if (purchase.getPrice().compareTo(vehicle.getMsrp()) == 1) {
            
            throw new Exception(
                    "Purchase price cannot exceed the MSRP.");
        }
        
        return purchaseDao.addPurchase(purchase);
    }
    
    public boolean validatePurchase(Purchase purchase, Vehicle vehicle) {
        if (purchase.getEmail() == null && purchase.getPhone() == null)
            return false;
        
        if (purchase.getEmail() != null){
            if (!validEmail(purchase.getEmail())){
                return false;
            }
        }
        
        if (!validPurchasePrice(purchase, vehicle))
            return false;
        
        return validZipCode(purchase);
    }
    
    private boolean validZipCode(Purchase purchase) {
        return purchase.getZipcode().matches("[0-9]{5}");
    }
    
    private boolean validPurchasePrice(Purchase purchase, Vehicle vehicle) { 
        return purchase.getPrice().compareTo(
        vehicle.getSalePrice().multiply(new BigDecimal("0.95"))) == -1 ||
            purchase.getPrice().compareTo(vehicle.getMsrp()) == 1;
                
    }

    private boolean validEmail(String email) {
        return Pattern.compile("^(.+)@(.+)$")
          .matcher(email)
          .matches();
        }
}
