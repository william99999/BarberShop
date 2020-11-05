package com.example.barbershop.Model;

public class User_Role {

    private int role_id;
    private String role_type;

    public User_Role() {
    }

    public User_Role(int role_id, String role_type) {
        this.role_id = role_id;
        this.role_type = role_type;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }
}
