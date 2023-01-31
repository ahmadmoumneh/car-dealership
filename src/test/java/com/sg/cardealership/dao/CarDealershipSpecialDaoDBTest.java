/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Special;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
public class CarDealershipSpecialDaoDBTest {
    
    @Autowired
    private CarDealershipSpecialDao specialDao;
    
    private Special special;
    
    private final String TITLE = "10% OFF OF FALL SALES";
    private final String DESCRIPTION = "Get 10% off of Winter sales and get a "
            + "hold of the newest cars that have this promotion. Quantity is "
            + "limited and offer ends in February 19th.";
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        special = specialDao.addSpecial(new Special(TITLE, DESCRIPTION));
    }
    
    @AfterAll
    public void tearDownClass() {
        specialDao.deleteSpecialById(special.getSpecialId());
        specialDao.resetAutoIncrement(0);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllSpecials method, of class CarDealershipSpecialDaoDB.
     */
    @Test
    public void testGetAllSpecials() {
        List<Special> specials = specialDao.getAllSpecials();
        
        assertFalse(specials.isEmpty());
    }

    /**
     * Test of addSpecial method, of class CarDealershipSpecialDaoDB.
     */
    @Test
    public void testAddSpecial() {
        Special promo = specialDao.addSpecial(new Special(
                 "35% OFF On Used SUVs", "Do you have a thing for "
                         + "SUVs? We have the best prices with the best "
                         + "discounts."));
        
        Special querried = specialDao.getSpecialById(promo.getSpecialId());
        
        assertNotNull(querried);
        assertEquals(promo, querried);
        
        boolean deleteSuccess = specialDao.deleteSpecialById(promo.getSpecialId());
        
        assertTrue(deleteSuccess);
    }

    /**
     * Test of deleteSpecialById method, of class CarDealershipSpecialDaoDB.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @Test
    public void testDeleteSpecialById() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        boolean deleteSuccess = specialDao.deleteSpecialById(
                special.getSpecialId());
        
        if (!deleteSuccess) {
            fail("Nothing was deleted.");
        }
            
        specialDao.addSpecial(special);
    }

    /**
     * Test of getSpecialById method, of class CarDealershipSpecialDaoDB.
     */
    @Test
    public void testGetSpecialById() {
        assertNotNull(special);
    }    
}
