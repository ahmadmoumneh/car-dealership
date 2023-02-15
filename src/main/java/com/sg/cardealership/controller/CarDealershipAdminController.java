/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.controller;

import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.Special;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.service.CarDealershipMakeService;
import com.sg.cardealership.service.CarDealershipModelService;
import com.sg.cardealership.service.CarDealershipSpecialService;
import com.sg.cardealership.service.CarDealershipUserService;
import com.sg.cardealership.service.CarDealershipVehicleService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Car Dealers
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cardealership/admin")
public class CarDealershipAdminController {
    @Autowired private CarDealershipSpecialService specialService;
    @Autowired private CarDealershipUserService userService;
    @Autowired private CarDealershipVehicleService vehicleService;
    @Autowired private CarDealershipModelService modelService;
    @Autowired private CarDealershipMakeService makeService;
    
    @PostMapping("/addvehicle")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        return this.vehicleService.addVehicle(vehicle);
    }
    
    @GetMapping("/vehicles")
    public List<Vehicle> searchVehicles(
            @RequestParam String value,
            @RequestParam String minPrice,
            @RequestParam String maxPrice,
            @RequestParam String minYear,
            @RequestParam String maxYear
    ) 
            throws IOException, SQLException {
        InventoryQuery query = new InventoryQuery(value, minPrice, maxPrice,
        minYear, maxYear, "Admin");
        
        return this.vehicleService.getVehicles(query);
    }
    
    @PostMapping("/addvehicle/uploadpicture/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean addPictureById(@PathVariable int id, @RequestBody byte[] picture) 
            throws SQLException, IOException {
        return this.vehicleService.addPictureById(id, picture);
    }
    
    @PutMapping("/editvehicle")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Vehicle editVehicle(@RequestBody Vehicle vehicle) 
            throws SQLException {
        return this.vehicleService.editVehicle(vehicle);
    }
    
    @PutMapping("/editvehicle/editpicture/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean editPictureById(@PathVariable int id, @RequestBody byte[] picture) 
            throws SQLException, IOException {
        
        return this.vehicleService.editPictureById(id, picture);
    }
    
    @DeleteMapping("/deletevehicle/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteVehicle(@PathVariable int id) throws IOException {
        return this.vehicleService.deleteVehicleById(id);
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }
    
    @GetMapping("/makes")
    public List<Make> getAllMakes() {
        return this.makeService.getAllMakes();
    }
    
    @GetMapping("/models")
    public List<Model> getAllModels() {
        return this.modelService.getAllModels();
    }
    
    @PostMapping("/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) throws SQLException {
        User addedUser = this.userService.addUser(user);
        
        if (addedUser == null)
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        
        return ResponseEntity.ok(addedUser);
    }
    
    @PutMapping("/edituser/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> editUser(User user) throws SQLException {
        boolean editedUser = this.userService.editUser(user);
        
        if (!editedUser)
            return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
        
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/addmake")
    @ResponseStatus(HttpStatus.CREATED)
    public Make addMake(@RequestBody Make make) throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        return this.makeService.addMake(make);
    }
    
    @PostMapping("/addmodel")
    @ResponseStatus(HttpStatus.CREATED)
    public Model addModel(@RequestBody Model model) throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        return this.modelService.addModel(model);
    }
    
    @PostMapping("/addspecial")
    @ResponseStatus(HttpStatus.CREATED)
    public Special addSpecial(@RequestBody Special special) {
        return this.specialService.addSpecial(special);
    }
    
    @DeleteMapping("/deletespecial/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteSpecial(@PathVariable int id) {
        return this.specialService.deleteSpecialById(id);
    }
}
