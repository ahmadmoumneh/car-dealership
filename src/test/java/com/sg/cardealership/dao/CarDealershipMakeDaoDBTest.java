/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class CarDealershipMakeDaoDBTest implements CarDealershipUserRole {
    
    @Autowired
    private MakeRepository makeDao;
    
    @Autowired
    private UserRepository userDao;
    
    private Make make;
    
    private final String MAKE_NAME = "Toyota";
    
    private User user;
    
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final String EMAIL = "john.doe@email.com";
    private final String PASSWORD = "abc123";
    
    private final LocalDate TODAY  = LocalDate.now();
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        user = userDao.save(new User(FIRST_NAME, LAST_NAME, 
                EMAIL, PASSWORD, ADMIN));
        
        make = makeDao.save(
                new Make(MAKE_NAME, user, TODAY));
    }
    
    @AfterAll
    public void tearDownClass() {
        makeDao.deleteById(make.getMakeId());
        userDao.deleteById(user.getUserId());
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMake method, of class CarDealershipMakeDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddMake() throws Exception {
        Make ford = makeDao.save(new Make("Ford", user, TODAY));
        
        Make querried = makeDao.findById(ford.getMakeId()).get();
        
        assertNotNull(querried);
        
        assertEquals(ford, querried);
        
        makeDao.deleteById(ford.getMakeId());
    }

    /**
     * Test of getAllMakes method, of class CarDealershipMakeDaoDB.
     */
    @Test
    public void testGetAllMakes() {
        List<Make> makes = makeDao.findAll();
        
        assertFalse(makes.isEmpty());
    }

    /**
     * Test of getMakeById method, of class CarDealershipMakeDaoDB.
     */
    @Test
    public void testGetMakeById() {
        Make querried = makeDao.findById(make.getMakeId()).get();
        
        assertNotNull(querried);
        assertEquals(make, querried);
    }
}
