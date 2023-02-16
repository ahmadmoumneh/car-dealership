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
    private ModelRepository modelDao;
    
    @Autowired
    private MakeRepository makeDao;
    
    @Autowired
    private UserRepository userDao;
    
    private Make make;
    private Model model;
    private User user;
    
    private final LocalDate TODAY  = LocalDate.now();
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        user = userDao.save(new User("John", "Doe", 
                "john.doe@email.com", "abc123", ADMIN));
        
        make = makeDao.save(
                new Make("BMW", user, TODAY));
        
        model = modelDao.save(
                new Model("BMW X7", make, user,
                        TODAY));
    }
    
    @AfterAll
    public void tearDownClass() {
        modelDao.deleteById(model.getModelId());
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
     * Test of addModel method, of class CarDealershipModelDaoDB.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testAddModel() throws Exception {
        
        Model bmw_x5 = modelDao.save(new Model("BMW X5", 
                make, user, TODAY));
        
        Model querried = modelDao.findById(bmw_x5.getModelId()).get();
        
        assertNotNull(querried);
        assertEquals(bmw_x5, querried);
        
        modelDao.deleteById(bmw_x5.getModelId());
    }

    /**
     * Test of getAllModels method, of class CarDealershipModelDaoDB.
     */
   
    @Test
    public void testGetAllModels() {
        List<Model> models = modelDao.findAll();
        
        assertFalse(models.isEmpty());
    }

    /**
     * Test of getModelById method, of class CarDealershipModelDaoDB.
     */
    
    @Test
    public void testGetModelById() {
        Model querried = modelDao.findById(model.getModelId()).get();
        
        assertNotNull(querried);
        assertEquals(model, querried);
    }
}
