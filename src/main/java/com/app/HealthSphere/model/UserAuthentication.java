package com.app.HealthSphere.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UserAuthentication {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String role; // Added role field
    private LocalDateTime lastLogin;
    private LocalDateTime accountCreated;
    private Boolean isActive;

    // Constructors
    public UserAuthentication() {}

    public UserAuthentication(Long userId,String username, String email, String passwordHash,  @JsonProperty(defaultValue = "USER") String role) {
        this.userId=userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role != null ? role : "USER"; // Default role is "USER"
        this.accountCreated = LocalDateTime.now();
        this.isActive = true;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(LocalDateTime accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
