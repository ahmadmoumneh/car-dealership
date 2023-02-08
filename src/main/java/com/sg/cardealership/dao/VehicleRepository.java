package com.sg.cardealership.dao;

import java.util.List;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.cardealership.dto.Make;
import com.sg.cardealership.dto.Model;
import com.sg.cardealership.dto.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{
    List<Vehicle> findByIsFeatured(boolean isFeatured);
    List<Vehicle> findByModel(Model model);
    List<Vehicle> findByYear(String year);
    
    // List<Vehicle> findSalePriceBetween(String minPrice, String maxPrice);
   // List<Vehicle> findYearBetween(String minYear, String maxYear);

}