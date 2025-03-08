package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.service.WorkoutRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutRecommendations")
public class WorkoutRecommendationsController {

    private final WorkoutRecommendationsService workoutRecommendationsService;

    @Autowired
    public WorkoutRecommendationsController(WorkoutRecommendationsService workoutRecommendationsService) {
        this.workoutRecommendationsService = workoutRecommendationsService;
    }

    // Create a new workout recommendation
    @PostMapping
    public ResponseEntity<String> createWorkoutRecommendation(
            @RequestBody WorkoutRecommendations workoutRecommendations) {
        workoutRecommendationsService.saveWorkoutRecommendation(workoutRecommendations);
        return new ResponseEntity<>("Workout recommendation created successfully", HttpStatus.CREATED);
    }

    // Retrieve all workout recommendations
    @GetMapping
    public ResponseEntity<List<WorkoutRecommendations>> getAllWorkoutRecommendations() {
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService
                .findAllWorkoutRecommendations();
        return new ResponseEntity<>(workoutRecommendations, HttpStatus.OK);
    }

    // Retrieve a workout recommendation by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutRecommendations> getWorkoutRecommendationById(@PathVariable int id) {
        WorkoutRecommendations workoutRecommendation = workoutRecommendationsService.findWorkoutRecommendationById(id);
        return new ResponseEntity<>(workoutRecommendation, HttpStatus.OK);
    }

    // Update a workout recommendation
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkoutRecommendation(@PathVariable int id,
            @RequestBody WorkoutRecommendations workoutRecommendations) {
        workoutRecommendations.setWorkoutId(id);
        workoutRecommendationsService.updateWorkoutRecommendation(workoutRecommendations);
        return new ResponseEntity<>("Workout recommendation updated successfully", HttpStatus.OK);
    }

    // Delete a workout recommendation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkoutRecommendation(@PathVariable int id) {
        workoutRecommendationsService.deleteWorkoutRecommendationById(id);
        return new ResponseEntity<>("Workout recommendation deleted successfully", HttpStatus.OK);
    }
}
