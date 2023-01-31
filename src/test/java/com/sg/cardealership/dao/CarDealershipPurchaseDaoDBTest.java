/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class CarDealershipPurchaseDaoDBTest implements CarDealershipUserRole {
    
    @Autowired
    private CarDealershipUserDao userDao;
    
    @Autowired
    private CarDealershipPurchaseDao purchaseDao;
    
    private User user;
    
    private final String SALES_FIRST_NAME = "John";
    private final String SALES_LAST_NAME = "Doe";
    private final String SALES_EMAIL = "john.doe@email.com";
    private final String SALES_PASSWORD = "password";
    
    private Purchase purchase;
    
    private final String NAME = "Bill Kyle";
    private final String PHONE = "216-234-4635";
    private final String EMAIL = "bill.kyle@email.com";
    private final String STREET1 = "street 1";
    private final String STREET2 = "street 2";
    private final String CITY = "New York";
    private final String STATE = "NY";
    private final String ZIPCODE = "23352";
    private final BigDecimal PRICE = new BigDecimal("10500");
    private final String PURCHASE_TYPE = "Cash";
    private final LocalDate DATE = LocalDate.now();
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        user = userDao.addUser(new User(SALES_FIRST_NAME, 
                SALES_LAST_NAME, SALES_EMAIL, SALES_PASSWORD, 
                SALES));
        
        purchase = purchaseDao.addPurchase(new Purchase(NAME, PHONE,
                EMAIL, STREET1, STREET2, CITY, STATE, ZIPCODE, PRICE,
                PURCHASE_TYPE,DATE, user));
       
    }
    
    @AfterAll
    public void tearDownClass() {
        purchaseDao.deletePurchaseById(purchase.getPurchaseId());
        purchaseDao.resetAutoIncrement(0);
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
     * Test of addPurchase method, of class CarDealershipPurchaseDaoDB.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddPurchase() throws SQLException {
        final String ADD_SALES_FIRST_NAME = "Jane";
        final String ADD_SALES_LAST_NAME = "Doe";
        final String ADD_SALES_EMAIL = "jane.doe@email.com";
        final String ADD_SALES_PASSWORD = "apple";
        
        User salesUser = userDao.addUser(new User(ADD_SALES_FIRST_NAME, 
                ADD_SALES_LAST_NAME,ADD_SALES_EMAIL, 
                ADD_SALES_PASSWORD, SALES));

        final String ADD_NAME = "Bill Kyle";
        final String ADD_PHONE = "216-234-4635";
        final String ADD_EMAIL = "bill.kyle@email.com";
        final String ADD_STREET1 = "street 1";
        final String ADD_STREET2 = "street 2";
        final String ADD_CITY = "New York";
        final String ADD_STATE = "NY";
        final String ADD_ZIPCODE = "23352";
        final BigDecimal ADD_PRICE = new BigDecimal("44000");
        final String ADD_PURCHASE_TYPE = "Bank Finance";
        final LocalDate ADD_DATE = LocalDate.now();
        
        Purchase newPurchase = purchaseDao.addPurchase(new Purchase(ADD_NAME,
                ADD_PHONE,ADD_EMAIL, ADD_STREET1, ADD_STREET2, 
                ADD_CITY,ADD_STATE, ADD_ZIPCODE, ADD_PRICE,
                ADD_PURCHASE_TYPE, ADD_DATE, salesUser));
        
        Purchase querried = 
                purchaseDao.getPurchaseById(newPurchase.getPurchaseId());
        
        assertNotNull(querried);
        assertEquals(newPurchase, querried);
                
        boolean deletePurchase = purchaseDao.deletePurchaseById(newPurchase.getPurchaseId());
        assertTrue(deletePurchase);
        
        boolean deleteUser = userDao.deleteUserById(salesUser.getUserId());
        assertTrue(deleteUser);
    }

    /**
     * Test of getPurchaseById method, of class CarDealershipPurchaseDaoDB.
     */
    @Test
    public void testGetPurchaseById() {
        Purchase querried = purchaseDao.getPurchaseById(purchase.getPurchaseId());
        
        assertNotNull(querried);
        assertEquals(purchase, querried);
    }
    
}
