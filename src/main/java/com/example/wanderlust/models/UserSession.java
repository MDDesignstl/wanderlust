package com.example.wanderlust.models;

public class UserSession {

    private Integer userId;

    private String username;

    private String name;

    private String email;

    public UserSession() {
    }

    public UserSession(Integer userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.name = username.substring(0, 1).toUpperCase() + username.substring(1);
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}