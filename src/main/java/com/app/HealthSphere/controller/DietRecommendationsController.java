package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.service.DietRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/dietRecommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class DietRecommendationsController {

    private final DietRecommendationsService dietRecommendationsService;

    @Autowired
    public DietRecommendationsController(DietRecommendationsService dietRecommendationsService) {
        this.dietRecommendationsService = dietRecommendationsService;
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // Create a new diet recommendation (Unchanged)
    @PostMapping
    public ResponseEntity<String> createDietRecommendation(@RequestBody DietRecommendations dietRecommendations) {
        dietRecommendationsService.saveDietRecommendation(dietRecommendations);
        return new ResponseEntity<>("Diet recommendation created successfully", HttpStatus.CREATED);
    }

    // Generate diet recommendations (Unchanged)
    @PostMapping("/generate")
    public ResponseEntity<List<DietRecommendations>> generateDietRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {
        List<DietRecommendations> generatedDiets = dietRecommendationsService.generateDietRecommendations(userId, goalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedDiets);
    }

    // Get recommendations by user and goal ID (Unchanged)
    @GetMapping("/get")
    public ResponseEntity<List<DietRecommendations>> getDietRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {
        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findDietsByUserAndGoalId(userId, goalId);
        return ResponseEntity.status(HttpStatus.OK).body(dietRecommendations);
    }

    // Get all recommendations by user ID (Unchanged)
    @GetMapping("/{userId}")
    public ResponseEntity<List<DietRecommendations>> getDietRecommendationById(@PathVariable int userId) {
        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findDietRecommendationById(userId);
        return new ResponseEntity<>(dietRecommendations, HttpStatus.OK);
    }

    // Retrieve all diet recommendations (Admin-only)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDietRecommendations(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied. Admins only."));
        }
        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findAllDietRecommendations();
        return ResponseEntity.ok(Map.of("dietRecommendations", dietRecommendations));
    }

    // Update a diet recommendation (Unchanged)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDietRecommendation(@PathVariable int id,
                                                           @RequestBody DietRecommendations dietRecommendations) {
        dietRecommendations.setDietId(id);
        dietRecommendationsService.updateDietRecommendation(dietRecommendations);
        return new ResponseEntity<>("Diet recommendation updated successfully", HttpStatus.OK);
    }

    // Delete a diet recommendation by ID (Unchanged)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDietRecommendation(@PathVariable int id) {
        dietRecommendationsService.deleteDietRecommendationById(id);
        return new ResponseEntity<>("Diet recommendation deleted successfully", HttpStatus.OK);
    }
}
