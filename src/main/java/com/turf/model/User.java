package com.turf.model;

public class User {
    // These match our database columns exactly
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    // 1. Default Constructor (Empty)
    // Why: Some Java tools need to create an "empty" object first and fill it later.
    public User() {}

    // 2. Full Constructor
    // Why: To create a User object in one line when we get data from the database.
    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // 3. Getters and Setters
    // Why: To safely read and update the private variables.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // 4. toString Method
    // Why: For debugging. Helps us see the user details in the console.
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}