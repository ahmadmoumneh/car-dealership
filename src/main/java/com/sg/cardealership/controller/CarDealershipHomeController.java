/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;

import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.dto.Contact;
import com.sg.cardealership.dto.Special;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.service.CarDealershipContactService;
import com.sg.cardealership.service.CarDealershipSpecialService;
import com.sg.cardealership.service.CarDealershipVehicleService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Car Dealers
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cardealership/home")
public class CarDealershipHomeController implements CarDealershipVehicleType {
    @Autowired private CarDealershipSpecialService specialService;
    @Autowired private CarDealershipVehicleService vehicleService;
    @Autowired private CarDealershipContactService contactService;
    
    @GetMapping("/specials")
    public List<Special> getAllSpecials() {
        return this.specialService.getAllSpecials();
    }
    
    @GetMapping("/featured")
    public List<Vehicle> getFeaturedVehicles() throws IOException, SQLException {
        return this.vehicleService.getVehicles(FEATURED);
    }
    
    @PostMapping("/addcontact")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact addContact(@RequestBody Contact contact) throws Exception {
        return this.contactService.addContact(contact);
    }
}
