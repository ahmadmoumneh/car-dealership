package com.sg.cardealership.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sg.cardealership.dto.Make;
public interface MakeRepo extends JpaRepository<Make, Integer> {
    
}
