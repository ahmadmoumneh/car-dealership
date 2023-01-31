/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.service;

import com.sg.cardealership.dao.CarDealershipContactDao;
import com.sg.cardealership.dto.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Car Dealers
 */
@Component
public class CarDealershipContactServiceImpl implements 
        CarDealershipContactService {
    
    @Autowired
    private CarDealershipContactDao contactDao;
    
    @Override
    public Contact addContact(Contact contact) throws Exception  {

        if (!inputIsValid(contact)) {
            throw new Exception("Contact details are not valid.");
        }
           
           
        return contactDao.addContact(contact);
    }
    
    private Boolean inputIsValid(Contact contact){
        if (contact.getName() == null || contact.getMessage() == null)
            return false;

        return !(contact.getEmail() == null && contact.getPhone() == null);
    }
    
}
