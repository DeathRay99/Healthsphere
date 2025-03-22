package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.service.DietRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dietRecommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class DietRecommendationsController {

    private final DietRecommendationsService dietRecommendationsService;

    @Autowired
    public DietRecommendationsController(DietRecommendationsService dietRecommendationsService) {
        this.dietRecommendationsService = dietRecommendationsService;
    }

    // Create a new diet recommendation
    @PostMapping
    public ResponseEntity<String> createDietRecommendation(@RequestBody DietRecommendations dietRecommendations) {
        dietRecommendationsService.saveDietRecommendation(dietRecommendations);
        return new ResponseEntity<>("Diet recommendation created successfully", HttpStatus.CREATED);
    }

//    @PostMapping("/generate")
//    public ResponseEntity<DietRecommendations> generateDietRecommendation(
//            @RequestParam int userId,
//            @RequestParam int goalId) {
//
//        DietRecommendations generatedDiets = dietRecommendationsService.generateDietRecommendation(userId, goalId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(generatedDiets);
//    }

    @PostMapping("/generate")
    public ResponseEntity<List<DietRecommendations>> generateDietRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {

        List<DietRecommendations> generatedDiets = dietRecommendationsService.generateDietRecommendations(userId, goalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedDiets);
    }

    //get recommendations by user and goalid
    @GetMapping("/get")
    public ResponseEntity<List<DietRecommendations>> getDietRecommendation(
            @RequestParam int userId,
            @RequestParam int goalId) {

        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findDietsByUserAndGoalId(userId, goalId);
        return ResponseEntity.status(HttpStatus.OK).body(dietRecommendations);
    }


    //get all recommendations by userid
    @GetMapping("/{userId}")
    public ResponseEntity<List<DietRecommendations>> getDietRecommendationById(@PathVariable int userId) {
        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findDietRecommendationById(userId);
        return new ResponseEntity<>(dietRecommendations, HttpStatus.OK);
    }


    // Retrieve all diet recommendations
    @GetMapping
    public ResponseEntity<List<DietRecommendations>> getAllDietRecommendations() {
        List<DietRecommendations> dietRecommendations = dietRecommendationsService.findAllDietRecommendations();
        return new ResponseEntity<>(dietRecommendations, HttpStatus.OK);
    }

    // Retrieve a diet recommendation by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<DietRecommendations> getDietRecommendationById(@PathVariable int id) {
//        DietRecommendations dietRecommendation = dietRecommendationsService.findDietRecommendationById(id);
//        return new ResponseEntity<>(dietRecommendation, HttpStatus.OK);
//    }

    // Update a diet recommendation
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDietRecommendation(@PathVariable int id,
            @RequestBody DietRecommendations dietRecommendations) {
        dietRecommendations.setDietId(id);
        dietRecommendationsService.updateDietRecommendation(dietRecommendations);
        return new ResponseEntity<>("Diet recommendation updated successfully", HttpStatus.OK);
    }

    // Delete a diet recommendation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDietRecommendation(@PathVariable int id) {
        dietRecommendationsService.deleteDietRecommendationById(id);
        return new ResponseEntity<>("Diet recommendation deleted successfully", HttpStatus.OK);
    }
}
