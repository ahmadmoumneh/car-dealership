/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipUserDao {
    List<User> getAllUsers();
    
    User getUserById(int id);
    
    User getUserByCredentials(String[] credentials);
    
    User addUser(User user) throws SQLException;
    
    boolean updateUser(User user) throws SQLException;
    
    boolean changePassword(User user);
    
    boolean deleteUserById(int id);
    
    boolean resetAutoIncrement(int startId);
}
