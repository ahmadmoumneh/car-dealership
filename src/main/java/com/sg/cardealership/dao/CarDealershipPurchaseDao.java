/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Purchase;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipPurchaseDao {
    Purchase addPurchase(Purchase purchase);
    
    Purchase getPurchaseById(int id);
    
    boolean deletePurchaseById(int id);
    
    boolean deleteAllPurchases();
    
    List<Purchase> getAllPurchases();
    
    boolean resetAutoIncrement(int startId);
}
