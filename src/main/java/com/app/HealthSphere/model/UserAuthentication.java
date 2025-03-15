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
    public UserAuthentication() {
        this.accountCreated = LocalDateTime.now();
        this.isActive = true;
    }

    public UserAuthentication(Long userId, String username, String email, String passwordHash,
                              @JsonProperty(defaultValue = "USER") String role) {
        try {
            setUserId(userId);
            setUsername(username);
            setEmail(email);
            setPasswordHash(passwordHash);
            setRole(role != null ? role : "USER"); // Default role is "USER"
            this.accountCreated = LocalDateTime.now();
            this.isActive = true;
        } catch (Exception e) {
            System.err.println("Error occurred while initializing UserAuthentication: " + e.getMessage());
            throw e;
        }
    }

    // Getters and Setters with Validation
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (username.length() < 3 || username.length() > 30) {
            throw new IllegalArgumentException("Username must be between 3 and 30 characters.");
        }
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty.");
        }
        if (passwordHash.length() < 8) {
            throw new IllegalArgumentException("Password hash must be at least 8 characters long.");
        }
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN"))) {
            throw new IllegalArgumentException("Role must be either 'USER' or 'ADMIN'.");
        }
        this.role = role.toUpperCase(); // Ensure consistency in role case
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        if (lastLogin != null && lastLogin.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Last login cannot be set to a future date.");
        }
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(LocalDateTime accountCreated) {
        if (accountCreated == null || accountCreated.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Account creation date cannot be null or in the future.");
        }
        this.accountCreated = accountCreated;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        if (isActive == null) {
            throw new IllegalArgumentException("Active status cannot be null.");
        }
        this.isActive = isActive;
    }

    // Debugging Utility: toString() Override
    @Override
    public String toString() {
        return "UserAuthentication{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", lastLogin=" + lastLogin +
                ", accountCreated=" + accountCreated +
                ", isActive=" + isActive +
                '}';
    }
}
