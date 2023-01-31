/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.cardealership.dao;

import static com.sg.cardealership.common.CarDealershipUserRole.ADMIN;
import com.sg.cardealership.common.CarDealershipVehicleType;
import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.sg.cardealership.dto.InventoryQuery.NO_MIN_PRICE;
import static com.sg.cardealership.dto.InventoryQuery.NO_MAX_YEAR;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Car Dealers
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class CarDealershipVehicleDaoDBTest implements CarDealershipVehicleType {
    
    @Autowired private CarDealershipVehicleDao vehicleDao;
    @Autowired private CarDealershipUserDao userDao;
    @Autowired private CarDealershipMakeDao makeDao;
    @Autowired private CarDealershipModelDao modelDao;
    
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
        
        vehicle2 = vehicleDao.addVehicle(new Vehicle(model, year2, bodyStyle2,
                transmission2, color2, interior2, mileage2, vin2, salePrice2,
                msrp2, description2));
        
        picture = convertFileToByteArray(
                        new File("./cars/bmw-x5.jpg")
                               .getCanonicalFile());
        
        vehicleDao.uploadPictureById(vehicle.getVehicleId(), picture);
        
        picture2 = convertFileToByteArray(
                        new File("./cars/bmw-x7-2019.jpg")
                               .getCanonicalFile());
        
        vehicleDao.uploadPictureById(vehicle2.getVehicleId(), picture2);
        
        userDao.addUser(USER);
        makeDao.addMake(MAKE);
        modelDao.addModel(MODEL);
        
        query = new InventoryQuery("BMW", 
            NO_MIN_PRICE, "40000", "2015", 
            NO_MAX_YEAR, "Sales");
    }
    
    @AfterAll
    public void tearDownClass() throws IOException {
        
        vehicleDao.deleteVehicleById(vehicle.getVehicleId());
        vehicleDao.deleteVehicleById(vehicle2.getVehicleId());
        vehicleDao.deleteVehicleById(vehicle3.getVehicleId());
        vehicleDao.deleteVehicleById(addVehicle.getVehicleId());
        vehicleDao.resetPictureAutoIncrement(0);
        vehicleDao.resetVehicleAutoIncrement(0);
        
        modelDao.deleteModelById(model.getModelId());
        modelDao.deleteModelById(MODEL.getModelId());
        modelDao.resetAutoIncrement(0);
        
        makeDao.deleteMakeById(make.getMakeId());
        makeDao.deleteMakeById(MAKE.getMakeId());
        makeDao.resetAutoIncrement(0);
        
        userDao.deleteUserById(user.getUserId());
        userDao.deleteUserById(USER.getUserId());
        userDao.resetAutoIncrement(0);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getVehicles method, of class CarDealershipVehicleDaoDB.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetVehicles_boolean() throws IOException, SQLException {
        vehicle.setIsFeatured(true);
        this.vehicleDao.updateVehicle(vehicle);
        
        List<Vehicle> vehicles = vehicleDao.getVehicles(true);
        
        assertFalse(vehicles.isEmpty());
        assertFalse(vehicles.stream().filter(v -> !v.isFeatured()).findAny().isPresent());
        
        vehicle.setIsFeatured(false);
    }

    /**
     * Test of getVehicles method, of class CarDealershipVehicleDaoDB.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetVehicles_InventoryQuery_String() throws IOException, SQLException {
        List<Vehicle> newVehicles = vehicleDao.getVehicles(query, NEW);
        assertFalse(newVehicles.isEmpty());
        
        assertTrue(newVehicles.stream().filter(v -> v.getVehicleType()
                .equals(USED)).findAny().isEmpty());
        
        List<Vehicle> usedVehicles = vehicleDao.getVehicles(query, USED);
        
        assertTrue(usedVehicles.isEmpty());
    }

    /**
     * Test of getVehicles method, of class CarDealershipVehicleDaoDB.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetVehicles_InventoryQuery() throws IOException, SQLException {
        InventoryQuery allQuery = new InventoryQuery("BMW", 
            NO_MIN_PRICE, "40000", "2015", 
            NO_MAX_YEAR, "Admin");
        
        List<Vehicle> vehicles = vehicleDao.getVehicles(allQuery);
        assertFalse(vehicles.isEmpty());
    }

    /**
     * Test of addVehicle method, of class CarDealershipVehicleDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddVehicle() throws Exception {
        addVehicle = addNewVehicle(addYear, addBodyStyle, 
                addTransmission,addColor, addInterior, 
                addMileage, addVin, addSalePrice, addMsrp,
                addDescription);
    }
    
    private Vehicle addNewVehicle(int year, String bodyStyle, 
            String transmission, String color, String interior, int mileage, 
            String vin, BigDecimal salePrice, BigDecimal msrp, 
            String description) 
            
            throws IOException, FileNotFoundException, SQLException {
        Vehicle addedVehicle = vehicleDao.addVehicle(new Vehicle(MODEL, 
                year, bodyStyle,transmission, 
                color, interior, mileage, vin,
                salePrice,msrp, description));
        
        vehicleDao.uploadPictureById(addedVehicle.getVehicleId(), picture);
        
        Vehicle querriedVehicle = vehicleDao.getVehicleById(
                addedVehicle.getVehicleId());
        
        assertNotNull(querriedVehicle);
        assertEquals(addedVehicle, querriedVehicle);
        
        return addedVehicle;
    }
    
    private void deleteVehicle(Vehicle vehicle) throws IOException {
        boolean deleteVehicleSuccess = vehicleDao.deleteVehicleById(
                vehicle.getVehicleId());
        
        assertTrue(deleteVehicleSuccess);
    }

    /**
     * Test of deleteVehicleById method, of class CarDealershipVehicleDaoDB.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @Test
    public void testDeleteVehicleById() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
        
        Vehicle querried = vehicleDao.getVehicleById(vehicle.getVehicleId());
        assertNotNull(querried);
        
        deleteVehicle(vehicle);
        vehicle = vehicleDao.addVehicle(vehicle);
        
        vehicleDao.uploadPictureById(vehicle.getVehicleId(), picture);
        
        Vehicle querriedVehicle = vehicleDao.getVehicleById(
                vehicle.getVehicleId());
        
        assertNotNull(querriedVehicle);
        assertEquals(vehicle, querriedVehicle);
    }
    
    /**
     * Test of testDeletePictureById method, of class CarDealershipVehicleDaoDB.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @Test
    public void testDeletePictureById() throws 
            IOException, 
            FileNotFoundException, 
            SQLException {
       
        boolean pictureDelete = vehicleDao.deletePictureById(vehicle2.getVehicleId());
        assertTrue(pictureDelete);
        
        boolean upload = vehicleDao.uploadPictureById(vehicle2.getVehicleId(), picture2);
        assertTrue(upload);
        
        byte[] querriedPicture = vehicleDao.getPictureById(
                vehicle2.getVehicleId());
        
        assertNotNull(querriedPicture);
        assertTrue(bytesAreEqual(picture2, querriedPicture));
    }
    
    private boolean bytesAreEqual(byte[] content1, byte[] content2) {
        return Arrays.compare(content1, content2) == 0;
    }

    /**
     * Test of updateVehicle method, of class CarDealershipVehicleDaoDB.
     * @throws java.lang.Exception
     */
     
   @Test
    public void testUpdateVehicle() throws Exception {
        final int updateYear = 2023;
        final String updateBodyStyle = "SUV";
        final String updateTransmission = "Automatic";
        final String updateColor = "Blue";
        final String updateInterior = "Black";
        final int updateMileage = 510;
        final String updateVin = "2SDF902WFEON223NF";
        final BigDecimal updateSalePrice = new BigDecimal("63000");
        final BigDecimal updateMsrp = new BigDecimal("65000");
        final String updateDescription = "This one is a beauty; "
                + "from inside out, comfortable, efficient and practical.";
        
        Vehicle shouldVehicleDataBe = new Vehicle(
            model,
            updateYear,
            updateBodyStyle,
            updateTransmission,
            updateColor,
            updateInterior,
            updateMileage,
            updateVin,
            updateSalePrice,    
            updateMsrp,
            updateDescription
        );
        
        shouldVehicleDataBe.setIsFeatured(true);
        shouldVehicleDataBe.setIsSold(true);
        
        shouldVehicleDataBe.setVehicleId(vehicle.getVehicleId());
        
        vehicle.setYear(updateYear);
        vehicle.setBodyStyle(updateBodyStyle);
        vehicle.setTransmission(updateTransmission);
        vehicle.setColor(updateColor);
        vehicle.setInterior(updateInterior);
        vehicle.setMileage(updateMileage);
        vehicle.setVin(updateVin);
        vehicle.setSalePrice(updateSalePrice);
        vehicle.setMsrp(updateMsrp);
        vehicle.setDescription(updateDescription);
        vehicle.setIsFeatured(true);
        vehicle.setIsSold(true);
        
        boolean updateSuccess = vehicleDao.updateVehicle(vehicle);
        
        assertTrue(updateSuccess);
        
        assertEquals(shouldVehicleDataBe,vehicle);
                
        vehicle.setYear(year);
        vehicle.setBodyStyle(bodyStyle);
        vehicle.setTransmission(transmission);
        vehicle.setColor(color);
        vehicle.setInterior(interior);
        vehicle.setMileage(mileage);
        vehicle.setVin(vin);
        vehicle.setSalePrice(salePrice);
        vehicle.setMsrp(msrp);
        vehicle.setDescription(description);
        vehicle.setIsFeatured(false);
        vehicle.setIsSold(false);
        
        updateSuccess = vehicleDao.updateVehicle(vehicle);
        
        assertTrue(updateSuccess);
    }
    
    /**
     * Test of updatePictureById method, of class CarDealershipVehicleDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatePictureById() throws Exception {
        File updatePictureFile = 
                new File("./cars/Fiat 500X.jpg").getCanonicalFile();
        
        byte[] updatePicture = 
                convertFileToByteArray(updatePictureFile);
        
        boolean updated = 
                vehicleDao.updatePictureById(vehicle.getVehicleId(), 
                        updatePicture);
        
        assertTrue(updated);
    }
    
    private byte[] convertFileToByteArray(File pictureFile) throws IOException {
        FileInputStream fileInputStream = 
                new FileInputStream(pictureFile);
        
        return fileInputStream.readAllBytes();
    }

    /**
     * Test of uploadPictureById method, of class CarDealershipVehicleDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadPictureById() throws Exception {
        vehicle3 = vehicleDao.addVehicle(new Vehicle(model, addYear, addBodyStyle, 
                addTransmission,addColor, addInterior, 
                addMileage, vin3, addSalePrice, addMsrp,
                addDescription));
        
        boolean success = 
                vehicleDao.uploadPictureById(vehicle3.getVehicleId(), 
                        picture);
        
        assertTrue(success);
    }

    /**
     * Test of getVehicleById method, of class CarDealershipVehicleDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVehicleById() throws Exception {
        Vehicle querried = vehicleDao.getVehicleById(vehicle.getVehicleId());
        
        assertNotNull(querried);
        assertEquals(vehicle, querried);
    }

}
