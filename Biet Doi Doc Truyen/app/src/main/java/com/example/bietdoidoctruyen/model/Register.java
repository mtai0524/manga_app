package com.example.bietdoidoctruyen.model;

public class Register {
    private int userId;
    private String username;
    private String password;
    private String avatarUri;
    private String role;

    public Register(int userId, String username, String avatarUri, String role) {
        this.userId = userId;
        this.username = username;
        this.avatarUri = avatarUri;
        this.role = role;
    }

    public Register(int userId, String username, String password, String avatarUri, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarUri = avatarUri;
        this.role = role;
    }

    public Register(int userId, String role) {
        this.userId = userId;
        this.role = role;

    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Register(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Register() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
