/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.Vehicle;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipPurchaseService {
    Purchase logPurchase(Purchase purchase, Vehicle vehicle) throws Exception;
    List<Purchase> getAllPurchases();
}
