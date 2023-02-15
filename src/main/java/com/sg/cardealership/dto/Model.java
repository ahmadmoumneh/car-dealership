/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.cardealership.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

import jakarta.persistence.*;

/**
 *
 * @author Car Dealers
 */
@Entity
public class Model {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int modelId;
    @Column
    private String modelName;
    @ManyToOne
    @JoinColumn (name="makeId")
    private Make modelMake;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User modelUser;
    @Column
    private LocalDate modelDate;
    
    public Model() {}
    
    public Model(String modelName, Make modelMake, User modelUser, 
            LocalDate modelDate) {
        this.modelName = modelName;
        this.modelMake = modelMake;
        this.modelUser = modelUser;
        this.modelDate = modelDate;
    }
    
    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public Make getModelMake() {
        return modelMake;
    }

    public User getModelUser() {
        return modelUser;
    }

    public LocalDate getModelDate() {
        return modelDate;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelMake(Make modelMake) {
        this.modelMake = modelMake;
    }

    public void setModelUser(User modelUser) {
        this.modelUser = modelUser;
    }

    public void setModelDate(LocalDate modelDate) {
        this.modelDate = modelDate;
    }
    
    public final static class ModelMapper implements RowMapper<Model> {
        @Override
        public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
            Model model = new Model();
            model.setModelId(rs.getInt("modelId"));
            model.setModelName(rs.getString("modelName"));
            model.setModelDate(rs.getDate("modelDate")
                    .toLocalDate());
            
            return model;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.modelId;
        hash = 73 * hash + Objects.hashCode(this.modelName);
        hash = 73 * hash + Objects.hashCode(this.modelMake);
        hash = 73 * hash + Objects.hashCode(this.modelUser);
        hash = 73 * hash + Objects.hashCode(this.modelDate);
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
        final Model other = (Model) obj;
        if (this.modelId != other.modelId) {
            return false;
        }
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        if (!Objects.equals(this.modelMake, other.modelMake)) {
            return false;
        }
        if (!Objects.equals(this.modelUser, other.modelUser)) {
            return false;
        }
        return Objects.equals(this.modelDate, other.modelDate);
    }

    @Override
    public String toString() {
        return "Model{" + "modelId=" + modelId + ", modelName=" + modelName + 
                ", modelMake=" + modelMake + ", modelUser=" + modelUser 
                + ", modelDate=" + modelDate + '}';
    }
}
