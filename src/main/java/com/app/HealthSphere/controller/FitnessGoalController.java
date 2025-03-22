//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.FitnessGoal;
//import com.app.HealthSphere.service.FitnessGoalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/fitnessGoals")
//@CrossOrigin(origins = "http://localhost:3000")
//public class FitnessGoalController {
//
//    private final FitnessGoalService fitnessGoalService;
//
//    @Autowired
//    public FitnessGoalController(FitnessGoalService fitnessGoalService) {
//        this.fitnessGoalService = fitnessGoalService;
//    }
//
//    // Create a new fitness goal
//    @PostMapping("/{userId}")
//    public ResponseEntity<String> createFitnessGoal(@PathVariable Long userId,@RequestBody FitnessGoal fitnessGoal) {
//        fitnessGoal.setUserId(userId);
//        fitnessGoalService.saveFitnessGoal(fitnessGoal);
//        return new ResponseEntity<>("Fitness goal created successfully", HttpStatus.CREATED);
//    }
//
//    // Retrieve all fitness goals
//    @GetMapping
//    public ResponseEntity<List<FitnessGoal>> getAllFitnessGoals() {
//        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoals();
//        return new ResponseEntity<>(fitnessGoals, HttpStatus.OK);
//    }
//
//    // Retrieve a fitness goal by ID
////    @GetMapping("/{id}")
////    public ResponseEntity<FitnessGoal> getFitnessGoalById(@PathVariable Long id) {
////        FitnessGoal fitnessGoal = fitnessGoalService.findFitnessGoalById(id);
////        return new ResponseEntity<>(fitnessGoal, HttpStatus.OK);
////    }
//
//    // Retrieve all goals of a user by userId
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<FitnessGoal>> getFitnessGoalsByUserId(@PathVariable Long userId) {
//        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoalsByUserId(userId);
//        return new ResponseEntity<>(fitnessGoals, HttpStatus.OK);
//    }
//
//    // Update a fitness goal
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateFitnessGoal(@PathVariable Long id, @RequestBody FitnessGoal fitnessGoal) {
//        fitnessGoal.setGoalId(id);
//        fitnessGoalService.updateFitnessGoal(fitnessGoal);
//        return new ResponseEntity<>("Fitness goal updated successfully", HttpStatus.OK);
//    }
//
//    // Delete a fitness goal by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteFitnessGoal(@PathVariable Long id) {
//        fitnessGoalService.deleteFitnessGoal(id);
//        return new ResponseEntity<>("Fitness goal deleted successfully", HttpStatus.OK);
//    }
//}

package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.FitnessGoal;
import com.app.HealthSphere.service.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fitnessGoals")
@CrossOrigin(origins = "http://localhost:3000")
public class FitnessGoalController {

    private final FitnessGoalService fitnessGoalService;

    @Autowired
    public FitnessGoalController(FitnessGoalService fitnessGoalService) {
        this.fitnessGoalService = fitnessGoalService;
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // Create a new fitness goal (User-only)
    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> createFitnessGoal(@PathVariable Long userId, @RequestBody FitnessGoal fitnessGoal, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. Users only."));
        }
        fitnessGoal.setUserId(userId);
        fitnessGoalService.saveFitnessGoal(fitnessGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Fitness goal created successfully."));
    }

    // Retrieve all fitness goals (Admin-only)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFitnessGoals(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. Admins only."));
        }
        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoals();
        return ResponseEntity.ok(Map.of("fitnessGoals", fitnessGoals));
    }

    // Retrieve all goals of a user by userId (User can see only their own goals)
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getFitnessGoalsByUserId(@PathVariable Long userId, @RequestHeader("Role") String role, @RequestHeader("UserId") Long loggedInUserId) {
        if (!isAdmin(role) && !userId.equals(loggedInUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. You can only view your own goals."));
        }
        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoalsByUserId(userId);
        return ResponseEntity.ok(Map.of("fitnessGoals", fitnessGoals));
    }

    // Update a fitness goal (User-only)
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFitnessGoal(@PathVariable Long id, @RequestBody FitnessGoal fitnessGoal, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. Users only."));
        }
        if (!fitnessGoal.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. You can only update your own fitness goals."));
        }
        fitnessGoal.setGoalId(id);
        fitnessGoalService.updateFitnessGoal(fitnessGoal);
        return ResponseEntity.ok(Map.of("message", "Fitness goal updated successfully."));
    }

    // Delete a fitness goal by ID (Accessible to both Admin and User)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFitnessGoal(@PathVariable Long id) {
        fitnessGoalService.deleteFitnessGoal(id);
        return ResponseEntity.ok(Map.of("message", "Fitness goal deleted successfully."));
    }
}
