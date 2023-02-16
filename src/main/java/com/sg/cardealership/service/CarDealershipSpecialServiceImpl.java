/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.SpecialRepository;
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
    private SpecialRepository specialDao;
    
    
    @Override
    public List<Special> getAllSpecials() {
        return specialDao.findAll();
    }

    @Override
    public Special addSpecial(Special special) {
        return specialDao.save(special);
    }

    @Override
    public void deleteSpecialById(int id) {
        specialDao.deleteById(id);
    }
    
}
