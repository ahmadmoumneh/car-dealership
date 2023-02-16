package com.sg.cardealership.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sg.cardealership.dto.Make;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<Make, Integer> {}
