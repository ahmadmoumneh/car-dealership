/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.cardealership.dto.Purchase;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahmad
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {}