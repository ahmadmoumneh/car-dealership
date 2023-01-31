/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dto.Special;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipSpecialService {
    List<Special> getAllSpecials();
    
    Special addSpecial(Special special);
    
    boolean deleteSpecialById(int id);
}
