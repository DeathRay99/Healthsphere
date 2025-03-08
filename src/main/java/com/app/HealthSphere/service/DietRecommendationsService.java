package com.app.HealthSphere.service;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.repository.DietRecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietRecommendationsService {

    private final DietRecommendationsRepository dietRecommendationsRepository;

    public DietRecommendationsService(DietRecommendationsRepository dietRecommendationsRepository) {
        this.dietRecommendationsRepository = dietRecommendationsRepository;
    }

    // Save diet recommendation
    public int saveDietRecommendation(DietRecommendations dietRecommendations) {
        return dietRecommendationsRepository.save(dietRecommendations);
    }

    // Find all diet recommendations
    public List<DietRecommendations> findAllDietRecommendations() {
        return dietRecommendationsRepository.findAll();
    }

    // Find diet recommendation by ID
    public DietRecommendations findDietRecommendationById(int dietId) {
        return dietRecommendationsRepository.findById(dietId);
    }

    // Update diet recommendation
    public int updateDietRecommendation(DietRecommendations dietRecommendations) {
        return dietRecommendationsRepository.update(dietRecommendations);
    }

    // Delete diet recommendation by ID
    public int deleteDietRecommendationById(int dietId) {
        return dietRecommendationsRepository.deleteById(dietId);
    }
}
