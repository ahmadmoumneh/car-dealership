/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.dao.CarDealershipUserDao;
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
    private CarDealershipUserDao userDao;
    
    private User currentUser;
    

    @Override
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Override
    public User addUser(User user) throws SQLException {
//        if (this.currentUser.getRole().equals(ADMIN)) {
            return this.userDao.addUser(user);
//        }
            
//        return null;
    }

    @Override
    public boolean editUser(User user) throws SQLException {
        if (this.currentUser.getRole().equals(ADMIN)) {
            return this.userDao.updateUser(user);
        }
        
        return false;
    }

    @Override
    public boolean changePassword(User user) {
        if (this.currentUser.getRole().equals(ADMIN) || 
                this.currentUser.getRole().equals(SALES)) 
            this.userDao.changePassword(user);
        
        return false;
    }

    @Override
    public User login(String[] credentials) {
        this.currentUser = this.userDao.getUserByCredentials(credentials);
        return this.currentUser;
    }

    @Override
    public void logout(int id) {
        this.currentUser = null;
    }
}