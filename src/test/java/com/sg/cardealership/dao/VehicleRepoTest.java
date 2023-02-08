package com.sg.cardealership.dao;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static com.sg.cardealership.common.CarDealershipUserRole.ADMIN;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.tool.schema.spi.SqlScriptException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.Vehicle;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)

public class VehicleRepoTest implements CarDealershipVehicleType {
    
    @Autowired private CarDealershipVehicleDao vehicleDao;
    @Autowired private CarDealershipUserDao userDao;
    @Autowired private CarDealershipMakeDao makeDao;
    @Autowired private CarDealershipModelDao modelDao;
    @Autowired private VehicleRepository vehicleRepo;
    
    private Vehicle vehicle;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private Vehicle addVehicle;
    
    private byte[] picture;
    private byte[] picture2;
    
    private User user;
    private Make make;
    private Model model;
    
    private final int year = 2016;
    private final String bodyStyle = "Car";
    private final String transmission = "Automatic";
    private final String color = "Blue";
    private final String interior = "Black";
    private final int mileage = 300;
    private final String vin = "13ADKF28371KJDAF8";
    private final BigDecimal salePrice = new BigDecimal("33000");
    private final BigDecimal msrp = new BigDecimal("35000");
    private final String description = "Small and sporty, with a hint of "
            + "luxury. This car is loaded with features including A/C, cruise "
            + "control, backup cameras and more!";
    
    private final int addYear = 2013;
    private final String addBodyStyle = "Car";
    private final String addTransmission = "Manual";
    private final String addColor = "Silver";
    private final String addInterior = "Gray";
    private final int addMileage = 3030;
    private final String addVin = "23097HDF90WRRV290";
    private final BigDecimal addSalePrice = new BigDecimal("41200");
    private final BigDecimal addMsrp = new BigDecimal("45000");
    private final String addDescription = "Fast and effecient, with lots of "
            + "style. This car can be of comfort while taking you places.";
    
    private final int year2 = 2023;
    private final String bodyStyle2 = "Truck";
    private final String transmission2 = "Automatic";
    private final String color2 = "Black";
    private final String interior2 = "Black";
    private final int mileage2 = 0;
    private final String vin2 = "RV2JOJPOFD23FLJ24";
    private final BigDecimal salePrice2 = new BigDecimal("153200");
    private final BigDecimal msrp2 = new BigDecimal("158000");
    private final String description2 = "Robust and sturdy. Helps you make way "
            + "through the toughest paths. This truck is fast and comes with style.";
    
    private final String vin3 = "WE9FOHFOWIU9334TL";
    
    private final LocalDate TODAY  = LocalDate.now();
    
    private final User USER = new User("James", "Smith", 
                "james.smith@email.com", "wegsd", ADMIN);
    
    private final Make MAKE = new Make("Toyota", USER, 
            TODAY);
    
    private final Model MODEL = new Model("Toyota Corolla", MAKE, 
            USER,TODAY);
    
    private InventoryQuery query;
    
    @BeforeAll
    public void setUpClass() throws SQLException, IOException {
        user = userDao.addUser(new User("John", "Doe", 
                "john.doe@email.com", "abc123", ADMIN));
        
        make = makeDao.addMake(
                new Make("BMW", user, TODAY));
        
        model = modelDao.addModel(
                new Model("BMW X5", make, user,
                        TODAY));
        
        vehicle = vehicleDao.addVehicle(new Vehicle(model, year, bodyStyle,
                transmission, color, interior, mileage, vin, salePrice,
                msrp, description));
        
    }

    @Test
    public void testFindById() throws SqlScriptException {
        Optional<Vehicle> vehicle = vehicleRepo.findById(1);
        assertNotNull(vehicle);
    }
}