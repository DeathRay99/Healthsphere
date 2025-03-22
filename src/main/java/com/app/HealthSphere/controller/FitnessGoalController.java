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
//@RequestMapping("/fitnessGoals")
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
//    @PostMapping
//    public ResponseEntity<String> createFitnessGoal(@RequestBody FitnessGoal fitnessGoal) {
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
//    @GetMapping("/{id}")
//    public ResponseEntity<FitnessGoal> getFitnessGoalById(@PathVariable Long id) {
//        FitnessGoal fitnessGoal = fitnessGoalService.findFitnessGoalById(id);
//        return new ResponseEntity<>(fitnessGoal, HttpStatus.OK);
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

@RestController
@RequestMapping("/fitnessGoals")
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
    @PostMapping
    public ResponseEntity<String> createFitnessGoal(@RequestBody FitnessGoal fitnessGoal, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
        }
        fitnessGoalService.saveFitnessGoal(fitnessGoal);
        return new ResponseEntity<>("Fitness goal created successfully", HttpStatus.CREATED);
    }

    // Retrieve all fitness goals (Admin-only)
    @GetMapping
    public ResponseEntity<List<FitnessGoal>> getAllFitnessGoals(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoals();
        return new ResponseEntity<>(fitnessGoals, HttpStatus.OK);
    }

    // Retrieve a fitness goal by ID (User can see only their own goal)
    @GetMapping("/{id}")
    public ResponseEntity<FitnessGoal> getFitnessGoalById(@PathVariable Long id, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
        FitnessGoal fitnessGoal = fitnessGoalService.findFitnessGoalById(id);
        if (isAdmin(role) || fitnessGoal.getUserId().equals(userId)) {
            return new ResponseEntity<>(fitnessGoal, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    // Update a fitness goal (User-only)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFitnessGoal(@PathVariable Long id, @RequestBody FitnessGoal fitnessGoal, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
        }
        if (!fitnessGoal.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. You can only update your own fitness goals.");
        }
        fitnessGoal.setGoalId(id);
        fitnessGoalService.updateFitnessGoal(fitnessGoal);
        return new ResponseEntity<>("Fitness goal updated successfully", HttpStatus.OK);
    }

    // Delete a fitness goal by ID (Accessible to both Admin and User)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFitnessGoal(@PathVariable Long id) {
        fitnessGoalService.deleteFitnessGoal(id);
        return new ResponseEntity<>("Fitness goal deleted successfully", HttpStatus.OK);
    }
}