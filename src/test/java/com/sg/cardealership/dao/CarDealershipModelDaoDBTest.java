/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
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
public class CarDealershipModelDaoDBTest implements CarDealershipUserRole {
    
    @Autowired
    private CarDealershipModelDao modelDao;
    
    @Autowired
    private CarDealershipMakeDao makeDao;
    
    @Autowired
    private CarDealershipUserDao userDao;
    
    private Make make;
    private Model model;
    private User user;
    
    private final LocalDate TODAY  = LocalDate.now();
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        user = userDao.addUser(new User("John", "Doe", 
                "john.doe@email.com", "abc123", ADMIN));
        
        make = makeDao.addMake(
                new Make("BMW", user, TODAY));
        
        model = modelDao.addModel(
                new Model("BMW X7", make, user,
                        TODAY));
    }
    
    @AfterAll
    public void tearDownClass() {
        modelDao.deleteModelById(model.getModelId());
        modelDao.resetAutoIncrement(0);
        makeDao.deleteMakeById(make.getMakeId());
        makeDao.resetAutoIncrement(0);
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
     * Test of addModel method, of class CarDealershipModelDaoDB.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testAddModel() throws Exception {
        
        Model bmw_x5 = modelDao.addModel(new Model("BMW X5", 
                make, user, TODAY));
        
        Model querried = modelDao.getModelById(bmw_x5.getModelId());
        
        assertNotNull(querried);
        assertEquals(bmw_x5, querried);
        
        boolean deleteSuccess = modelDao.deleteModelById(bmw_x5.getModelId());
        
        assertTrue(deleteSuccess);
    }

    /**
     * Test of getAllModels method, of class CarDealershipModelDaoDB.
     */
   
    @Test
    public void testGetAllModels() {
        List<Model> models = modelDao.getAllModels();
        
        assertFalse(models.isEmpty());
    }

    /**
     * Test of getModelById method, of class CarDealershipModelDaoDB.
     */
    
    @Test
    public void testGetModelById() {
        Model querried = modelDao.getModelById(model.getModelId());
        
        assertNotNull(querried);
        assertEquals(model, querried);
    }
}
