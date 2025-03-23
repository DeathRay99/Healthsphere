package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.UserAuthentication;
import com.app.HealthSphere.service.UserAuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserAuthenticationController {

    private final UserAuthenticationService authService;

    public UserAuthenticationController(UserAuthenticationService authService) {
        this.authService = authService;
    }

    // Register a new user
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserAuthentication user) {
//        boolean success = authService.register(user);
//        if (success) {
//            return ResponseEntity.ok("User registered successfully!");
//        }
//        return ResponseEntity.badRequest().body("Username already taken.");
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserAuthentication user, HttpSession session) {
        // Check if email is already in use
        if (authService.getUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already taken."));
        }

        // Check if username is already taken
        if (authService.getUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken."));
        }

        // Proceed with registration
        boolean success = authService.register(user);

        if (success) {
            Optional<UserAuthentication> userOptional = authService.getUserByUsername(user.getUsername());

            if (userOptional.isPresent()) {
                UserAuthentication registeredUser = userOptional.get();

                session.setAttribute("username", registeredUser.getUsername());
                session.setAttribute("userId", registeredUser.getUserId());
                session.setAttribute("role", registeredUser.getRole());

                Map<String, Object> response = new HashMap<>();
                response.put("message", "User registered successfully!");
                response.put("username", registeredUser.getUsername());
                response.put("userId", registeredUser.getUserId());
                response.put("role", registeredUser.getRole());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.badRequest().body(Map.of("err", "Registration failed. Please try again."));
    }



    // Login user and create session
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserAuthentication user, HttpSession session) {
        boolean isAuthenticated = authService.authenticate(user.getUsername(), user.getPasswordHash());

        if (isAuthenticated) {
            Optional<UserAuthentication> userOptional = authService.getUserByUsername(user.getUsername());
            if (userOptional.isPresent()) {
                UserAuthentication authenticatedUser = userOptional.get();

                session.setAttribute("username", authenticatedUser.getUsername());
                session.setAttribute("userId", authenticatedUser.getUserId());
                session.setAttribute("role", authenticatedUser.getRole());

                authService.updateLastLogin(authenticatedUser.getUsername());

                // ✅ Use HashMap to avoid NullPointerException
                Map<String, Object> response = new HashMap<>();
                response.put("username", authenticatedUser.getUsername());
                response.put("userId", authenticatedUser.getUserId());
                response.put("role", authenticatedUser.getRole());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.badRequest().body(Map.of("err", "Invalid credentials"));
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

        return ResponseEntity.badRequest().body(Map.of("err", "No user is logged in."));
    }
}

