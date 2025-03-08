package com.app.HealthSphere.service;

import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.repository.WorkoutRecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutRecommendationsService {

    private final WorkoutRecommendationsRepository workoutRecommendationsRepository;

    public WorkoutRecommendationsService(WorkoutRecommendationsRepository workoutRecommendationsRepository) {
        this.workoutRecommendationsRepository = workoutRecommendationsRepository;
    }

    // Save workout recommendation
    public int saveWorkoutRecommendation(WorkoutRecommendations workoutRecommendations) {
        return workoutRecommendationsRepository.save(workoutRecommendations);
    }

    // Find all workout recommendations
    public List<WorkoutRecommendations> findAllWorkoutRecommendations() {
        return workoutRecommendationsRepository.findAll();
    }

    // Find workout recommendation by ID
    public WorkoutRecommendations findWorkoutRecommendationById(int workoutId) {
        return workoutRecommendationsRepository.findById(workoutId);
    }

    // Update workout recommendation
    public int updateWorkoutRecommendation(WorkoutRecommendations workoutRecommendations) {
        return workoutRecommendationsRepository.update(workoutRecommendations);
    }

    // Delete workout recommendation by ID
    public int deleteWorkoutRecommendationById(int workoutId) {
        return workoutRecommendationsRepository.deleteById(workoutId);
    }
}
