/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipSpecialDao;
import com.sg.cardealership.dto.Special;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipSpecialServiceImpl implements 
       CarDealershipSpecialService {
    
    @Autowired
    private CarDealershipSpecialDao specialDao;
    
    
    @Override
    public List<Special> getAllSpecials() {
        return specialDao.getAllSpecials();
    }

    @Override
    public Special addSpecial(Special special) {
        return specialDao.addSpecial(special);
    }

    @Override
    public boolean deleteSpecialById(int id) {
        return specialDao.deleteSpecialById(id);
    }
    
}
