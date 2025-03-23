package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.service.WorkoutRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/workoutRecommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkoutRecommendationsController {

    private final WorkoutRecommendationsService workoutRecommendationsService;

    @Autowired
    public WorkoutRecommendationsController(WorkoutRecommendationsService workoutRecommendationsService) {
        this.workoutRecommendationsService = workoutRecommendationsService;
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // ✅ Create a new workout recommendation (Unchanged)
    @PostMapping
    public ResponseEntity<Map<String, Object>> createWorkoutRecommendation(
            @RequestBody WorkoutRecommendations workoutRecommendations) {
        workoutRecommendationsService.saveWorkoutRecommendation(workoutRecommendations);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", "Workout recommendation created successfully."));
    }

    // ✅ Generate workout recommendations (Unchanged)
    @PostMapping("/generate")
    public ResponseEntity<List<WorkoutRecommendations>> generateWorkoutRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {
        List<WorkoutRecommendations> generatedWorkouts = workoutRecommendationsService.generateMultipleWorkoutRecommendations(userId, goalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedWorkouts);
    }

    // ✅ Get recommendations by user and goal ID (Unchanged)
    @GetMapping("/get")
    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutsByUserAndGoalId(userId, goalId);
        return ResponseEntity.status(HttpStatus.OK).body(workoutRecommendations);
    }

    // ✅ Retrieve all workout recommendations (Admin-only)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllWorkoutRecommendations(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Admins only."));
        }
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findAllWorkoutRecommendations();
        return ResponseEntity.ok(Map.of("workoutRecommendations", workoutRecommendations));
    }

    // ✅ Retrieve a workout recommendation by userID (Unchanged)
    @GetMapping("/{userId}")
    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendationById(@PathVariable int userId) {
        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutRecommendationById(userId);
        return new ResponseEntity<>(workoutRecommendations, HttpStatus.OK);
    }

    // ✅ Update a workout recommendation (Unchanged)
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateWorkoutRecommendation(@PathVariable int id,
                                                                           @RequestBody WorkoutRecommendations workoutRecommendations) {
        workoutRecommendations.setWorkoutId(id);
        workoutRecommendationsService.updateWorkoutRecommendation(workoutRecommendations);
        return ResponseEntity.ok(Map.of("response", "Workout recommendation updated successfully."));
    }

    // ✅ Delete a workout recommendation by ID (Unchanged)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteWorkoutRecommendation(@PathVariable int id) {
        workoutRecommendationsService.deleteWorkoutRecommendationById(id);
        return ResponseEntity.ok(Map.of("response", "Workout recommendation deleted successfully."));
    }
}
//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.WorkoutRecommendations;
//import com.app.HealthSphere.service.WorkoutRecommendationsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("api/workoutRecommendations")
//@CrossOrigin(origins = "http://localhost:3000")
//public class WorkoutRecommendationsController {
//
//    private final WorkoutRecommendationsService workoutRecommendationsService;
//
//    @Autowired
//    public WorkoutRecommendationsController(WorkoutRecommendationsService workoutRecommendationsService) {
//        this.workoutRecommendationsService = workoutRecommendationsService;
//    }
//
//    private boolean isAdmin(String role) {
//        return "ADMIN".equalsIgnoreCase(role);
//    }
//
//    private boolean isUser(String role) {
//        return "USER".equalsIgnoreCase(role);
//    }
//
//    // ✅ Create a new workout recommendation (Unchanged)
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> createWorkoutRecommendation(
//            @RequestBody WorkoutRecommendations workoutRecommendations) {
//        workoutRecommendationsService.saveWorkoutRecommendation(workoutRecommendations);
//        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", "Workout recommendation created successfully."));
//    }
//
//    // ✅ Generate workout recommendations (Accessible to both USER and ADMIN)
//    @PostMapping("/generate")
//    public ResponseEntity<List<WorkoutRecommendations>> generateWorkoutRecommendation(
//            @RequestParam int userId,
//            @RequestParam int goalId,
//            @RequestHeader("Role") String role) {
//        if (!isUser(role) && !isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of());
//        }
//        List<WorkoutRecommendations> generatedWorkouts = workoutRecommendationsService.generateMultipleWorkoutRecommendations(userId, goalId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(generatedWorkouts);
//    }
//
//    // ✅ Get recommendations by user and goal ID (Accessible to both USER and ADMIN)
//    @GetMapping("/get")
//    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendation(
//            @RequestParam int userId,
//            @RequestParam int goalId,
//            @RequestHeader("Role") String role) {
//        if (!isUser(role) && !isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of());
//        }
//        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutsByUserAndGoalId(userId, goalId);
//        return ResponseEntity.status(HttpStatus.OK).body(workoutRecommendations);
//    }
//
//    // ✅ Retrieve all workout recommendations (Accessible to both USER and ADMIN)
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getAllWorkoutRecommendations(@RequestHeader("Role") String role) {
//        if (!isUser(role) && !isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Invalid role."));
//        }
//        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findAllWorkoutRecommendations();
//        return ResponseEntity.ok(Map.of("workoutRecommendations", workoutRecommendations));
//    }
//
//    // ✅ Retrieve a workout recommendation by user ID (Accessible to both USER and ADMIN)
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<WorkoutRecommendations>> getWorkoutRecommendationById(
//            @PathVariable int userId,
//            @RequestHeader("Role") String role) {
//        if (!isUser(role) && !isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of());
//        }
//        List<WorkoutRecommendations> workoutRecommendations = workoutRecommendationsService.findWorkoutRecommendationById(userId);
//        return new ResponseEntity<>(workoutRecommendations, HttpStatus.OK);
//    }
//
//    // ✅ Update a workout recommendation (Accessible to ADMIN only)
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> updateWorkoutRecommendation(
//            @PathVariable int id,
//            @RequestBody WorkoutRecommendations workoutRecommendations,
//            @RequestHeader("Role") String role) {
//        if (!isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Admins only."));
//        }
//        workoutRecommendations.setWorkoutId(id);
//        workoutRecommendationsService.updateWorkoutRecommendation(workoutRecommendations);
//        return ResponseEntity.ok(Map.of("response", "Workout recommendation updated successfully."));
//    }
//
//    // ✅ Delete a workout recommendation by ID (Accessible to ADMIN only)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> deleteWorkoutRecommendation(
//            @PathVariable int id,
//            @RequestHeader("Role") String role) {
//        if (!isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Admins only."));
//        }
//        workoutRecommendationsService.deleteWorkoutRecommendationById(id);
//        return ResponseEntity.ok(Map.of("response", "Workout recommendation deleted successfully."));
//    }
//}
//
