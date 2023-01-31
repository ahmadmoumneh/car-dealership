/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.common.CarDealershipUserRole;
import static com.sg.cardealership.common.CarDealershipUserRole.ADMIN;
import com.sg.cardealership.dto.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Car Dealers
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class CarDealershipUserDaoDBTest implements CarDealershipUserRole {
    
    @Autowired
    private CarDealershipUserDao userDao;
    
    private User user;
    
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final String EMAIL = "john.doe@email.com";
    private final String PASSWORD = "password";
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        user = userDao.addUser(new User(FIRST_NAME, LAST_NAME, 
                EMAIL, PASSWORD, ADMIN));
    }
    
    @AfterAll
    public void tearDownClass() {
        userDao.deleteUserById(user.getUserId());
        userDao.resetAutoIncrement(0);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class CarDealershipUserDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUser() throws Exception {
        User matt = userDao.addUser(new User("Max", "Sims", 
                "max.sims@email.com", PASSWORD, ADMIN));
        
        User querried = userDao.getUserById(matt.getUserId());
        
        assertNotNull(querried);
        assertEquals(matt, querried);
        
        boolean deleteSuccess = userDao.deleteUserById(matt.getUserId());
        
        assertTrue(deleteSuccess);
    }

    /**
     * Test of getAllUsers method, of class CarDealershipUserDaoDB.
     */
    @Test
    public void testGetAllUsers() {
        List<User> users = userDao.getAllUsers();
        
        assertFalse(users.isEmpty());
    }

    /**
     * Test of getUserById method, of class CarDealershipUserDaoDB.
     */
    @Test
    public void testGetUserById() {
        User querried = userDao.getUserById(user.getUserId());
        
        assertNotNull(querried);
        assertEquals(user, querried);
    }
    
    /**
     * Test of getUserByCredentials method, of class CarDealershipUserDaoDB.
     */
    @Test
    public void testGetUserByCredentials() {
        String[] credentials = {user.getEmail(), user.getPassword()};
        
        User querried = userDao.getUserByCredentials(credentials);
        assertNotNull(querried);
    }
    
    /**
     * Test of changePassword method, of class CarDealershipUserDaoDB.
     */
    @Test
    public void testChangePassword() {
        final String NEW_PASSWORD = "1234";
        
        user.setPassword(NEW_PASSWORD);
        
        boolean changeSuccess = userDao.changePassword(user);
        
        assertTrue(changeSuccess);
        
        User userWhoChangedPassword = userDao.getUserById(user.getUserId());
        
        assertNotNull(userWhoChangedPassword);
        
        assertEquals(NEW_PASSWORD, 
                userWhoChangedPassword.getPassword());
        
        user.setPassword(PASSWORD);
        
        changeSuccess = userDao.changePassword(user);
        
        assertTrue(changeSuccess);
        
        userWhoChangedPassword = userDao.getUserById(user.getUserId());
        assertNotNull(userWhoChangedPassword);
        
        assertEquals(PASSWORD, userWhoChangedPassword.getPassword());
    }
    
    @Test
    public void testUpdateUser() throws SQLException {
        final String NEW_FIRST_NAME = "Matt";
        final String NEW_LAST_NAME = "Rogers";
        final String NEW_EMAIL = "matt.rogers@email.com";
        final String NEW_PASSWORD = "zxcv";
        final String NEW_ROLE = SALES;
        
        User shouldUserDataBe = new User(
            NEW_FIRST_NAME,
            NEW_LAST_NAME,
            NEW_EMAIL,
            NEW_PASSWORD,
            NEW_ROLE
        );
        
        shouldUserDataBe.setUserId(user.getUserId());
        
        user.setFirstName(NEW_FIRST_NAME);
        user.setLastName(NEW_LAST_NAME);
        user.setEmail(NEW_EMAIL);
        user.setPassword(NEW_PASSWORD);
        user.setRole(NEW_ROLE);
        
        boolean updateSuccess = userDao.updateUser(user);
        
        assertTrue(updateSuccess);
        
        assertEquals(shouldUserDataBe,user);
                
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ADMIN);
        
        updateSuccess = userDao.updateUser(user);
        
        assertTrue(updateSuccess);
    }
}
