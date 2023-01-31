/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.dto.InventoryQuery;
import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Make.MakeMapper;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.Model.ModelMapper;
import com.sg.cardealership.dto.User;
import com.sg.cardealership.dto.Vehicle;
import com.sg.cardealership.dto.Vehicle.VehicleMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Car Dealers
 */
@Repository
public class CarDealershipVehicleDaoDB implements 
        CarDealershipVehicleDao {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override // get FEATURED or ALL vehicles
    public List<Vehicle> getVehicles(boolean isFeatured) throws 
            IOException, SQLException {
        
        final String sql = "SELECT * FROM vehicle  WHERE isFeatured = ?";
        List<Vehicle> vehicles = jdbc.query(sql, new VehicleMapper(), isFeatured? 1 : 0);
        loadVehicles(vehicles);
        return vehicles;
    }

    @Override // get featured NEW or USED vehicles depending on the query
    public List<Vehicle> getVehicles(InventoryQuery query, String vehicleType) throws 
            IOException, SQLException {
        
        String SEARCH_SQL = " AND (vehicleType = '" + vehicleType +"')";
        query.appendSql(SEARCH_SQL);
        System.out.println(query.getSql());
        List<Vehicle> vehicles = jdbc.query(query.getSql(), new VehicleMapper());
        loadVehicles(vehicles);
        
        return vehicles;
    }
    
    @Override // get SOME vehicles depending on the query
    public List<Vehicle> getVehicles(InventoryQuery query) throws 
            IOException, SQLException {
        
        try {
            query.generateSql();
            List<Vehicle> vehicles = jdbc.query(query.getSql(), 
                new VehicleMapper());
        
            loadVehicles(vehicles);
            return vehicles;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) throws FileNotFoundException,
            IOException, SQLException {
        
        final String insertVehicle = 
                "INSERT INTO vehicle(vehicleType, modelId, year, bodyStyle, " +
                "transmission, color, interior, mileage, vin, salePrice, msrp, "+
                "description) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = 
                    conn.prepareStatement(insertVehicle, 
                            Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, vehicle.getVehicleType());
            statement.setInt(2, vehicle.getModel().getModelId());
            statement.setInt(3, vehicle.getYear());
            statement.setString(4, vehicle.getBodyStyle());
            statement.setString(5, vehicle.getTransmission());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getInterior());
            statement.setInt(8, vehicle.getMileage());
            statement.setString(9, vehicle.getVin());
            statement.setBigDecimal(10, vehicle.getSalePrice());
            statement.setBigDecimal(11, vehicle.getMsrp());
            statement.setString(12, vehicle.getDescription());
            
            
            return statement;
        }, kh);
        
        Number key = kh.getKey();
        
        if (key != null)
            vehicle.setVehicleId(key.intValue());
        
        else
            throw new SQLException("Vehicle ID was null.");
        
        return vehicle;
    }

    @Override
    @Transactional
    public boolean deleteVehicleById(int id) throws IOException {
        boolean deletePicture = deletePictureById(id);
        final String DELETE_BY_ID = "DELETE FROM vehicle where vehicleId = ? ";
        return jdbc.update(DELETE_BY_ID, id) > 0 && deletePicture;
    }

    @Override
    @Transactional
    public boolean updateVehicle(Vehicle vehicle)
            throws SQLException {
        System.out.println(vehicle);
        final String EDIT_VEHICLE = "UPDATE vehicle SET vehicleType = ?, " +
        "year = ?, bodyStyle = ?, transmission = ?, color = ?, interior = ?, " +
        "mileage = ?, vin = ?, salePrice = ?, msrp = ?, description = ?, "+
        "modelId = ?, isFeatured = ?, isSold = ? WHERE vehicleId = ?";
        
        return jdbc.update(EDIT_VEHICLE,
        vehicle.getVehicleType(),
        vehicle.getYear(),
        vehicle.getBodyStyle(), 
        vehicle.getTransmission(),
        vehicle.getColor(),
        vehicle.getInterior(),
        vehicle.getMileage(),
        vehicle.getVin(),
        vehicle.getSalePrice(),
        vehicle.getMsrp(),
        vehicle.getDescription(),
        vehicle.getModel().getModelId(),
        vehicle.isFeatured(),
        vehicle.isSold(),
        vehicle.getVehicleId()) > 0;
    }
    
    private boolean savePictureById(int id, byte[] picture, String sql) throws 
            IOException, SQLException {
        Blob blob = new SerialBlob(picture);
        
        boolean saveSuccess = jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBlob(1, blob);
            statement.setInt(2, id);
            
            return statement;
        }) > 0;
        
        return saveSuccess && loadPictureById(id);
    }
    
    private boolean loadPictureById(int id) throws 
            IOException, 
            SQLException {
        File picture = 
                new File("./ui/src/assets/images/inventory-" + id + ".jpg")
                        .getCanonicalFile();
        
        if (!picture.exists()) {
            picture.createNewFile();
        }
            
        byte[] content = getPictureById(id);
        
        try (FileOutputStream fileOutStream = new FileOutputStream(picture)) {
            fileOutStream.write(content);
        } catch (FileNotFoundException e) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public byte[] getPictureById(int id) throws SQLException {
        try {
            final String sql = "SELECT content FROM picture WHERE vehicleId = ?;";
            Blob blob = 
                    jdbc.queryForObject(sql, Blob.class, id);

            return blob.getBytes(1, (int) blob.length());
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Vehicle getVehicleById(int id) throws IOException, SQLException {
        final String sql = "SELECT * FROM vehicle WHERE vehicleId = ?";

        Vehicle vehicle = jdbc.queryForObject(sql,
                new VehicleMapper(), id);

        addModelForVehicle(vehicle);
        loadPictureById(id);
        System.out.println(vehicle);
        return vehicle;
    }
    
    private void addMakeForModel(Model model) {
        final String sql = "SELECT mk.* FROM make mk "
                + "JOIN model md ON mk.makeId = md.makeId "
                 + "WHERE md.modelId = ?";
         
        Make make = jdbc.queryForObject(sql, new MakeMapper(), 
               model.getModelId());
        
        addUserForMake(make);
        
        model.setModelMake(make);
    }
    
    private void addUserForMake(Make make) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN make m ON u.userId = m.userId WHERE m.makeId = ?";
        User user = jdbc.queryForObject(sql, new User.UserMapper(), 
                make.getMakeId());
        
        make.setMakeUser(user);
    }
    
    private void addUserForModel(Model model) {
        final String sql = "SELECT u.* FROM user u "
                + "JOIN model m ON u.userId = m.userId WHERE m.modelId = ?";
        
        User user = jdbc.queryForObject(sql, new User.UserMapper(), 
                model.getModelId());
        
        model.setModelUser(user);
    }
    
    private void addModelForVehicle(Vehicle vehicle) {
        final String sql = "SELECT m.* FROM model m "
                + "JOIN vehicle v ON m.modelId = v.modelId "
                + "WHERE v.vehicleId = ?";
        
        Model model = jdbc.queryForObject(sql, new ModelMapper(), 
                vehicle.getVehicleId());
        
        addMakeForModel(model);
        addUserForModel(model);
        
        vehicle.setModel(model);
    }
    
    private void loadVehicles(List<Vehicle> vehicles) throws 
            IOException, 
            SQLException {
        
        for (Vehicle vehicle : vehicles) {
            addModelForVehicle(vehicle);
            loadPictureById(vehicle.getVehicleId());
        }
    }
    
    @Override
    public boolean resetVehicleAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE vehicle AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }
    
    @Override
    public boolean resetPictureAutoIncrement(int startId) {
        final String sql = 
                "ALTER TABLE picture AUTO_INCREMENT = ?";
        
        return jdbc.update(sql, startId) > 0;
    }

    @Override
    @Transactional
    public boolean deletePictureById(int id) throws IOException {
        File asset = 
                new File("./ui/src/assets/images/inventory-" + 
                        id + ".jpg").getCanonicalFile();
        
        boolean deleteAsset = asset.delete();
        
        final String DELETE_BY_ID = "DELETE FROM picture where vehicleId = ? ";
        return jdbc.update(DELETE_BY_ID, id) > 0 && deleteAsset;
    }

    @Override
    @Transactional
    public boolean uploadPictureById(int id, byte[] picture) throws IOException, SQLException {
        final String INSERT_PICTURE = 
                "INSERT INTO picture(content, vehicleId) VALUES (?,?)";
        return savePictureById(id, picture, INSERT_PICTURE);
    }

    @Override
    @Transactional
    public boolean updatePictureById(int id, byte[] picture) throws IOException, SQLException {
        final String UPDATE_PICTURE = 
                "UPDATE picture SET content = ? WHERE vehicleId = ?";
        
        return savePictureById(id, picture, UPDATE_PICTURE);
    }

    
}
