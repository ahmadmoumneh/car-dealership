/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.Contact;

/**
 *
 * @author Car Dealers
 */
public interface CarDealershipContactDao {
    Contact addContact(Contact contact);
    
    Contact getContactById(int id);
    
    boolean deleteContactById(int id);
    
    int resetAutoIncrement(int startId);
}
