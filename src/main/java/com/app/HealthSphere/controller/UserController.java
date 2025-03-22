package com.app.HealthSphere.controller;

import com.app.HealthSphere.exception.UserNotFoundException;
import com.app.HealthSphere.model.User;
import com.app.HealthSphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // ✅ Create a user (User-only)
    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> createUser(@PathVariable Long userId, @RequestBody User user, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Users only."));
        }
        userService.saveUser(userId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", "User created successfully."));
    }

    // ✅ Retrieve all users (Admin-only)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Admins only."));
        }
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(Map.of("users", users));
    }

    // ✅ Retrieve user profile (Users only)
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long userId, @RequestHeader("Role") String role, @RequestHeader("UserId") Long loggedInUserId) {
        if (!isAdmin(role) && !userId.equals(loggedInUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. You can only view your own profile."));
        }
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(Map.of("user", user));
    }

    // ✅ Update user profile (User-only)
    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long userId, @RequestBody User user, @RequestHeader("Role") String role, @RequestHeader("UserId") Long loggedInUserId) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Users only."));
        }
        if (!userId.equals(loggedInUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. You can only update your own profile."));
        }
        user.setUserId(userId);
        userService.updateUser(user);
        return ResponseEntity.ok(Map.of("response", "User updated successfully."));
    }

    // ✅ Delete user profile (Admin and User)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(Map.of("response", "User deleted successfully."));
    }
}
