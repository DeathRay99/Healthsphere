package com.app.HealthSphere.service;

import com.app.HealthSphere.model.UserAuthentication;
import com.app.HealthSphere.repository.UserAuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService {

    private final UserAuthenticationRepository userRepository;

    public UserAuthenticationService(UserAuthenticationRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public boolean register(UserAuthentication user) {
        Optional<UserAuthentication> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false; // Username already exists
        }
        return userRepository.save(user) > 0;
    }

    // Authenticate user (for login)
    public boolean authenticate(String username, String password) {
        Optional<UserAuthentication> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserAuthentication user = userOptional.get();
            return user.getPasswordHash().equals(password); // Simple string comparison
        }
        return false;
    }


    // Update last login time
    public void updateLastLogin(String username) {
        userRepository.updateLastLogin(username);
    }
}
