/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Contact;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class CarDealershipContactDaoDBTest {
    
    @Autowired
    private CarDealershipContactDao contactDao;
    
    private Contact contact;
    
    private final String NAME = "John Doe";
    private final String EMAIL = "john.doe@email.com";
    private final String PHONE = "216-234-4635";
    private final String MESSAGE = "Hello, my name is John Doe. I would like " +
            "to purchase a BMW X7 of the year 2016. Please call me back. " +
            "Have a nice day!";
    
    @BeforeAll
    public void setUpClass() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        contact = contactDao.addContact(new Contact(NAME, PHONE,
                EMAIL, MESSAGE));
    }
    
    @AfterAll
    public void tearDownClass() {
        contactDao.deleteContactById(contact.getContactId());
        contactDao.resetAutoIncrement(0);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addContact method, of class CarDealershipContactDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddContact() throws Exception {
        Contact jane = contactDao.addContact(new Contact("Jane Doe", 
                "212-456-2463", "jane.doe@email.com", "Hi, my"
                        + " name is Jane Doe, I would like to browse for a new"
                        + " car."));
        
        Contact querried = contactDao.getContactById(jane.getContactId());
        
        assertNotNull(querried);
        assertEquals(jane, querried);
        
        boolean deleteSuccess = contactDao.deleteContactById(jane.getContactId());
        
        assertTrue(deleteSuccess);
        
    }

    /**
     * Test of getUserById method, of class CarDealershipUserDaoDB.
     */
    @Test
    public void testGetContactById() {
        Contact querried = contactDao.getContactById(contact.getContactId());
        assertEquals(contact, querried);
    }
}
