/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Special;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipSpecialDao {
    List<Special> getAllSpecials();
    
    Special addSpecial(Special special);
    
    boolean deleteSpecialById(int id);
    
    Special getSpecialById(int id);
    
    boolean resetAutoIncrement(int startId);
}
