package com.gitbitex.marketdata.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class User {
    private String id;
    private Date createdAt;
    
    private boolean role;
    // Getter method for the 'role' property
    public boolean getRole() {
        return role;
    }
     @Override
    public String toString() {
        return "User{id='" + id + "', createdAt=" + createdAt + ", role=" + role + '}';
    }
}
