package com.sg.cardealership.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.cardealership.dto.Model;

@Repository
public interface ModelRepo extends JpaRepository<Model, Integer> {

}
