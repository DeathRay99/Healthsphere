//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.exception.UserNotFoundException;
//import com.app.HealthSphere.model.User;
//import com.app.HealthSphere.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/users")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @PostMapping("/{userId}")
//    public ResponseEntity<String> createUser(@PathVariable Long userId, @RequestBody User user) {
//        userService.saveUser(userId, user);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.findAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
//        User user = userService.findUserById(userId);
//        return ResponseEntity.ok(user);
//    }
//
//    // ✅ Update a user (Handles user not found)
//    @PutMapping("/{userId}")
//    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User user) {
//        user.setUserId(userId);
//        userService.updateUser(user);
//        return ResponseEntity.ok("User updated successfully.");
//    }
//
//    // ✅ Delete a user (Handles user not found)
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully.");
//}
//}

package com.app.HealthSphere.controller;

import com.app.HealthSphere.exception.UserNotFoundException;
import com.app.HealthSphere.model.User;
import com.app.HealthSphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> createUser(@PathVariable Long userId, @RequestBody User user, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
        }
        userService.saveUser(userId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    // ✅ Retrieve all users (Admin-only)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // ✅ Retrieve user profile (Users only)
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    // ✅ Update user profile (User-only)
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User user, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
        }
        user.setUserId(userId);
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully.");
    }

    // ✅ Delete user profile (Admin and User)
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId, @RequestHeader("Role") String role) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}

