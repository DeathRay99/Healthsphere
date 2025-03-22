package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.service.WorkoutRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workoutRecommendations")
@CrossOrigin(origins = "http://localhost:3000")
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

//    @PostMapping("/generate")
//    public ResponseEntity<WorkoutRecommendations> generateWorkoutRecommendation(
//            @RequestParam int userId,
//            @RequestParam int goalId) {
//
//            WorkoutRecommendations generatedWorkout = workoutRecommendationsService.generateWorkoutRecommendation(userId, goalId);
//            return ResponseEntity.status(HttpStatus.CREATED).body(generatedWorkout);
//    }

    @PostMapping("/generate")
    public ResponseEntity<List<WorkoutRecommendations>> generateWorkoutRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {

        List<WorkoutRecommendations> generatedWorkouts = workoutRecommendationsService.generateMultipleWorkoutRecommendations(userId, goalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedWorkouts);
    }

    //get recommendations by user and goalid
    @GetMapping("/get")
    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {

        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutsByUserAndGoalId(userId, goalId);
        return ResponseEntity.status(HttpStatus.OK).body(workoutRecommendations);
    }


    // Retrieve all workout recommendations
    @GetMapping
    public ResponseEntity<List<WorkoutRecommendations>> getAllWorkoutRecommendations() {
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService
                .findAllWorkoutRecommendations();
        return new ResponseEntity<>(workoutRecommendations, HttpStatus.OK);
    }

    // Retrieve a workout recommendation by userID
    @GetMapping("/{userId}")
    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendationById(@PathVariable int userId) {
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutRecommendationById(userId);
        return new ResponseEntity<>(workoutRecommendations, HttpStatus.OK);
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
