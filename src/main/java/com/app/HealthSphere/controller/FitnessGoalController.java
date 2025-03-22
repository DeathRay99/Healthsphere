package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.FitnessGoal;
import com.app.HealthSphere.service.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fitnessGoals")
@CrossOrigin(origins = "http://localhost:3000")
public class FitnessGoalController {

    private final FitnessGoalService fitnessGoalService;

    @Autowired
    public FitnessGoalController(FitnessGoalService fitnessGoalService) {
        this.fitnessGoalService = fitnessGoalService;
    }

    // Create a new fitness goal
    @PostMapping("/{userId}")
    public ResponseEntity<String> createFitnessGoal(@PathVariable Long userId,@RequestBody FitnessGoal fitnessGoal) {
        fitnessGoal.setUserId(userId);
        fitnessGoalService.saveFitnessGoal(fitnessGoal);
        return new ResponseEntity<>("Fitness goal created successfully", HttpStatus.CREATED);
    }

    // Retrieve all fitness goals
    @GetMapping
    public ResponseEntity<List<FitnessGoal>> getAllFitnessGoals() {
        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoals();
        return new ResponseEntity<>(fitnessGoals, HttpStatus.OK);
    }

    // Retrieve a fitness goal by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<FitnessGoal> getFitnessGoalById(@PathVariable Long id) {
//        FitnessGoal fitnessGoal = fitnessGoalService.findFitnessGoalById(id);
//        return new ResponseEntity<>(fitnessGoal, HttpStatus.OK);
//    }

    // Retrieve all goals of a user by userId
    @GetMapping("/{userId}")
    public ResponseEntity<List<FitnessGoal>> getFitnessGoalsByUserId(@PathVariable Long userId) {
        List<FitnessGoal> fitnessGoals = fitnessGoalService.findAllFitnessGoalsByUserId(userId);
        return new ResponseEntity<>(fitnessGoals, HttpStatus.OK);
    }

    // Update a fitness goal
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFitnessGoal(@PathVariable Long id, @RequestBody FitnessGoal fitnessGoal) {
        fitnessGoal.setGoalId(id);
        fitnessGoalService.updateFitnessGoal(fitnessGoal);
        return new ResponseEntity<>("Fitness goal updated successfully", HttpStatus.OK);
    }

    // Delete a fitness goal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFitnessGoal(@PathVariable Long id) {
        fitnessGoalService.deleteFitnessGoal(id);
        return new ResponseEntity<>("Fitness goal deleted successfully", HttpStatus.OK);
    }
}
