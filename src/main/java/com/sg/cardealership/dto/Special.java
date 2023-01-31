/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Car Dealers
 */
public class Special {
    private int specialId;
    private String title;
    private String description;
    
    public Special() {}

    public Special(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getSpecialId() {
        return specialId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setSpecialId(int id) {
        this.specialId = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public final static class SpecialMapper implements RowMapper<Special> {
        @Override
        public Special mapRow(ResultSet rs, int rowNum) throws SQLException {
            Special special = new Special();
            special.setSpecialId(rs.getInt("specialId"));
            special.setTitle(rs.getString("title"));
            special.setDescription(rs.getString("description"));
            
            return special;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.specialId;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Special other = (Special) obj;
        if (this.specialId != other.specialId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

    @Override
    public String toString() {
        return "Special{" + "id=" + specialId + ", title=" + title + 
                ", description=" + description + '}';
    }
}
