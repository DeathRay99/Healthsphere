package com.app.HealthSphere.service;

import com.app.HealthSphere.model.UserAuthentication;
import com.app.HealthSphere.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService {

    private final UserAuthenticationRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenticationService(UserAuthenticationRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register a new user
    public boolean register(UserAuthentication user) {
        Optional<UserAuthentication> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false; // Username already exists
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

        return userRepository.save(user) > 0;
    }

    // Authenticate user (for login)
    public boolean authenticate(String username, String password) {
        Optional<UserAuthentication> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserAuthentication user = userOptional.get();
            // Use BCrypt to verify the password
            return passwordEncoder.matches(password, user.getPasswordHash());
        }
        return false;
    }

    // ✅ Get user details by username (for login response)
    public Optional<UserAuthentication> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Update last login time
    public void updateLastLogin(String username) {
        userRepository.updateLastLogin(username);
    }
}
