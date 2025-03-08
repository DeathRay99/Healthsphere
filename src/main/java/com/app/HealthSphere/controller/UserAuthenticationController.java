package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.UserAuthentication;
import com.app.HealthSphere.service.UserAuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    private final UserAuthenticationService authService;

    public UserAuthenticationController(UserAuthenticationService authService) {
        this.authService = authService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthentication user) {
        boolean success = authService.register(user);
        if (success) {
            return ResponseEntity.ok("User registered successfully!");
        }
        return ResponseEntity.badRequest().body("Username already taken.");
    }

    // Login user and create session
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserAuthentication user, HttpSession session) {
        boolean isAuthenticated = authService.authenticate(user.getUsername(), user.getPasswordHash());

        if (isAuthenticated) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole()); // Store role in session

            authService.updateLastLogin(user.getUsername());

            return ResponseEntity.ok(Map.of(
                    "username", user.getUsername(),
                    "userId", user.getUserId(),
                    "role", user.getRole()
            ));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
    }


    // Logout user and destroy session
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Destroy session
        return ResponseEntity.ok("Logged out successfully!");
    }

    // Get the logged-in user's info
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (username != null && userId != null && role != null) {
            return ResponseEntity.ok(Map.of(
                    "username", username,
                    "userId", userId,
                    "role", role
            ));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "No user is logged in."));
    }
}

