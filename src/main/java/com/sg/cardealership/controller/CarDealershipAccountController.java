/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;

import com.sg.cardealership.dto.User;
import com.sg.cardealership.service.CarDealershipUserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Car Dealers
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cardealership/account")
public class CarDealershipAccountController {
    
    @Autowired
    private CarDealershipUserService userService;
    
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> login(@RequestParam String username, 
            @RequestParam String password) {
        
        User loggingUser = this.userService.login(username, password);
        
        Optional<ResponseEntity> response = 
                Optional.of(ResponseEntity.ok(loggingUser));
       
        return response.orElse(new ResponseEntity(null, HttpStatus.UNAUTHORIZED));
    }
}