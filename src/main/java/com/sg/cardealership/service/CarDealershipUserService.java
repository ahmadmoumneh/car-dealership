/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dto.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipUserService {
    List<User> getAllUsers();
    
    User addUser(User user) throws SQLException;
    
    User editUser(User user) throws SQLException;
    
    User login(String username, String password);
}
