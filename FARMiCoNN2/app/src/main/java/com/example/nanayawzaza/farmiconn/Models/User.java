package com.example.nanayawzaza.farmiconn.Models;


public class User {
    //User variables
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    //User constructor

    public User() {
    }

    public User(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // User Set Methods
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    // User get method
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getPhoneNumber() {return phoneNumber;}
}
