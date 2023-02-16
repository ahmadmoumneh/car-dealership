/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.dao.UserRepository;
import com.sg.cardealership.dto.User;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipUserServiceImpl implements 
        CarDealershipUserService, CarDealershipUserRole {
    
    @Autowired
    private UserRepository userDao;
    
    private User currentUser;
    

    @Override
    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    @Override
    public User addUser(User user) throws SQLException {
//        if (this.currentUser.getRole().equals(ADMIN)) {
            return this.userDao.save(user);
//        }
            
//        return null;
    }

    @Override
    public User editUser(User user) throws SQLException {
        if (this.currentUser.getRole().equals(ADMIN)) {
            return this.userDao.save(user);
        }
        
        return null;
    }

    @Override
    public User login(String username, String password) {
        
        this.currentUser = this.userDao.findByEmailAndPassword(username, password);
        return this.currentUser;
    }
}