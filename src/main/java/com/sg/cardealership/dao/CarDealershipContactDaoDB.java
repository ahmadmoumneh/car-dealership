/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Contact;
import com.sg.cardealership.dto.Contact.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Car Dealers
 */

@Repository
public class CarDealershipContactDaoDB implements CarDealershipContactDao {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Contact addContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(name, phone, email, message) " + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_CONTACT, 
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getMessage());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;    
    } 

    @Override
    public Contact getContactById(int id) {
        try {
            final String SELECT_CONTACT_BY_ID = 
                "SELECT * FROM contact WHERE contactId = ?";
        
            return jdbc.queryForObject(
                            SELECT_CONTACT_BY_ID, new ContactMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean deleteContactById(int id) {
        final String DELETE_CONTACT = "DELETE FROM contact WHERE contactId = ?";
        return jdbc.update(DELETE_CONTACT, id) > 0;
    }

    @Override
    public int resetAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE contact AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId);
    }
}
