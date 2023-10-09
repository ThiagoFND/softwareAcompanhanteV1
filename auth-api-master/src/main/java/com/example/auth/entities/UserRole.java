package com.example.auth.entities;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    ACOMPANHANTE("acompanhante");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
