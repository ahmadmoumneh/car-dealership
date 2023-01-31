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

/**
 *
 * @author Car Dealers
 */
public class Make {
    private int makeId;
    private String makeName;
    private User makeUser;
    private LocalDate makeDate;
    
    public Make(){}
        
    public Make(String makeName, User makeUser, LocalDate makeDate) {
        this.makeName = makeName;
        this.makeUser = makeUser;
        this.makeDate = makeDate;
    }
    
    public int getMakeId() {
        return makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public User getMakeUser() {
        return makeUser;
    }

    public LocalDate getMakeDate() {
        return makeDate;
    }

    public void setMakeId(int id) {
        this.makeId = id;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public void setMakeUser(User makeUser) {
        this.makeUser = makeUser;
    }

    public void setMakeDate(LocalDate makeDate) {
        this.makeDate = makeDate;
    }
    
    public final static class MakeMapper implements RowMapper<Make> {
        @Override
        public Make mapRow(ResultSet rs, int rowNum) throws SQLException {
            Make make = new Make();
            make.setMakeId(rs.getInt("makeId"));
            make.setMakeName(rs.getString("makeName"));
            make.setMakeDate(rs.getDate("makeDate")
                    .toLocalDate());
            return make;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.makeId;
        hash = 41 * hash + Objects.hashCode(this.makeName);
        hash = 41 * hash + Objects.hashCode(this.makeUser);
        hash = 41 * hash + Objects.hashCode(this.makeDate);
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
        final Make other = (Make) obj;
        if (this.makeId != other.makeId) {
            return false;
        }
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.makeUser, other.makeUser)) {
            return false;
        }
        return Objects.equals(this.makeDate, other.makeDate);
    }

    @Override
    public String toString() {
        return "Make{" + "id=" + makeId + ", makeName=" + makeName + ", makeUser="
                + makeUser + ", makeDate=" + makeDate + '}';
    }
}
