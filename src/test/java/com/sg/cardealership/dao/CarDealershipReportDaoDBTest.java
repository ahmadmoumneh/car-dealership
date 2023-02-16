/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.common.CarDealershipDateFormatter;
import com.sg.cardealership.common.CarDealershipUserRole;
import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.common.CarDearlershipPurchaseType;
import com.sg.cardealership.dto.InventoryReport;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.Purchase;
import com.sg.cardealership.dto.SalesReport;
import com.sg.cardealership.dto.SalesReportQuery;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.Vehicle;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class CarDealershipReportDaoDBTest implements 
        CarDealershipUserRole,
        CarDearlershipPurchaseType,
        CarDealershipDateFormatter,
        CarDealershipVehicleType {
    
    @Autowired private CarDealershipReportDao reportDao;
    @Autowired private UserRepository userDao;
    @Autowired private PurchaseRepository purchaseDao;
    @Autowired private MakeRepository makeDao;
    @Autowired private ModelRepository modelDao;
    @Autowired private VehicleRepository vehicleDao;
    
    private Vehicle vehicle;
    private Vehicle vehicle2;
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
    
    private final String transmission2 = "Manual";
    private final String color2 = "Navy";
    private final int mileage2 = 200;
    private final String vin2 = "WE9FOHFOWIU9334TL";
    private final BigDecimal salePrice2 = new BigDecimal("44000");
    private final BigDecimal msrp2 = new BigDecimal("46000");
    
    private final LocalDate TODAY  = LocalDate.now();
    
    @BeforeAll
    public void setUpClass() throws SQLException, IOException {
        
        user = userDao.save(new User("Mary", "Simons", 
                "mary.simons@email.com", "vcbdr", SALES));
        
        purchaseDao.save(new Purchase("Tom", "218-232-3456", 
                "tom@email.com", "Park Avenue", "App. 505", "New York", 
                "NY", "25035", new BigDecimal("23000"), CASH, 
                LocalDate.now(), user));
        
        purchaseDao.save(new Purchase("Sally", "213-675-0463", 
                "sally@email.com", "Mulholland Drive", "Unit. 1612", "Los Angeles", 
                "CA", "09456", new BigDecimal("162000"), BANK_FINANCE, 
                LocalDate.parse("2020-04-23", DATE_FORMATTER), user));
        
        make = makeDao.save(
                new Make("BMW", user, TODAY));
        
        model = modelDao.save(
                new Model("BMW X5", make, user,
                        TODAY));
        
        vehicle = vehicleDao.save(new Vehicle(model, year, bodyStyle,
                transmission, color, interior, mileage, vin, salePrice,
                msrp, description));
        
        vehicle2 = vehicleDao.save(new Vehicle(model, year, bodyStyle,
                transmission2, color2, interior, mileage2, vin2, salePrice2,
                msrp2, description));
    }
    
    @AfterAll
    public void tearDownClass() throws IOException {
        purchaseDao.deleteAll();
        
        vehicleDao.deleteById(vehicle.getVehicleId());
        vehicleDao.deleteById(vehicle2.getVehicleId());
        
        modelDao.deleteById(model.getModelId());
        
        makeDao.deleteById(make.getMakeId());
        
        userDao.deleteById(user.getUserId());
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of generateSalesReport method, of class CarDealershipReportDaoDB.
     */
    @Test
    public void testGenerateSalesReport() {
        List<User> users = userDao.findAll();
        
        SalesReportQuery query = new SalesReportQuery(users,
                LocalDate.parse("2018-12-06",DATE_FORMATTER),
                LocalDate.parse("2023-07-18",DATE_FORMATTER)
        );
        
        SalesReport salesReport = this.reportDao.generateSalesReport(query);
        
        assertEquals("Mary Simons", salesReport.getUserName());
    }

    /**
     * Test of generateInventoryReport method, of class CarDealershipReportDaoDB.
     */
    @Test
    public void testGenerateInventoryReport() {
        List<InventoryReport> inventoryReports = 
                this.reportDao.generateInventoryReport(NEW);
        
        assertEquals(1, inventoryReports.size());
        
        
    }
    
}
