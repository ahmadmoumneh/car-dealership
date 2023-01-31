/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipMakeDao;
import com.sg.cardealership.dto.Make;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipMakeServiceImpl implements 
       CarDealershipMakeService {
    
    @Autowired
    private  CarDealershipMakeDao makeDao;

    @Override
    public Make addMake(Make make) throws FileNotFoundException, 
            IOException, SQLException {
        
        return makeDao.addMake(make);
    }

    @Override
    public List<Make> getAllMakes() {
        return makeDao.getAllMakes();
    }

    @Override
    public Make getMakeById(int id) {
        return makeDao.getMakeById(id);
    }
    
}
