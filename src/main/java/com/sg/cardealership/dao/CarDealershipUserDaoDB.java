/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.User.UserMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Car Dealers
 */

@Repository
public class CarDealershipUserDaoDB implements CarDealershipUserDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * FROM user";
        return jdbc.query(sql, new UserMapper());
    }

    @Override
    @Transactional
    public User addUser(User user) throws SQLException {
        final String sql = "INSERT INTO user(firstName, lastName, email, " +
                "password, role) VALUES (?,?,?,?,?)";
        
        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            
            return statement;
        }, kh);
        
        Number key = kh.getKey();
        
        if (key != null)
            user.setUserId(key.intValue());
        else 
            throw new SQLException("User id not found.");
        
        return user;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) throws SQLException {
        final String sql = "UPDATE user SET firstName = ?, lastName = ?, " + 
                "email = ?, password = ?, role = ? WHERE userId = ?";
        
        return jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getUserId());
            
            return statement;
        }) > 0;
    }
    
    @Override
    @Transactional
    public boolean changePassword(User user) {
        final String sql = "UPDATE user SET password = ? WHERE userId = ?";
        return jdbc.update(sql, user.getPassword(), user.getUserId()) > 0;
    }

    @Override
    public User getUserByCredentials(String[] credentials) {
        try {
            final String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

            return jdbc.queryForObject(sql, new UserMapper(), credentials[0],
                credentials[1]);
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteUserById(int id) { 
        final String DELETE_USER = "DELETE FROM user WHERE userId = ?";
        return jdbc.update(DELETE_USER, id) > 0;
    }
    
    @Override
    public boolean resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE user AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }

    @Override
    public User getUserById(int id) {
        try {
            final String sql = "SELECT * FROM user WHERE userId = ?";
            return jdbc.queryForObject(sql, new UserMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
