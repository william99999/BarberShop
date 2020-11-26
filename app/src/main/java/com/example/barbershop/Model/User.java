package com.example.barbershop.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String name, username, email, phoneNumber, password;
    private Integer role_id;
    private Integer user_id;

    public User() {
    }
//    String user_table = "CREATE TABLE " + USER_TABLE + " (user_id INTEGER PRIMARY KEY, name TEXT, username TEXT, email TEXT, phonenumber TEXT, password TEXT, role_id INTEGER)";

    public User( int user_id , String name, String username, String email, String phoneNumber, String password, int role_id) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role_id = role_id;
        this.user_id = user_id;
    }

    public User(String name, String phoneNumber, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return user_id + "\t" + name ;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
