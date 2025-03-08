package com.app.HealthSphere.service;

import com.app.HealthSphere.model.FitnessGoal;
import com.app.HealthSphere.repository.FitnessGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessGoalService {

    private final FitnessGoalRepository fitnessGoalRepository;

    public FitnessGoalService(FitnessGoalRepository fitnessGoalRepository) {
        this.fitnessGoalRepository = fitnessGoalRepository;
    }

    // Save a new fitness goal
    public int saveFitnessGoal(FitnessGoal goal) {
        return fitnessGoalRepository.save(goal);
    }

    // Retrieve all fitness goals
    public List<FitnessGoal> findAllFitnessGoals() {
        return fitnessGoalRepository.findAll();
    }

    // Find a fitness goal by ID
    public FitnessGoal findFitnessGoalById(Long goalId) {
        return fitnessGoalRepository.findById(goalId);
    }

    // Update an existing fitness goal
    public int updateFitnessGoal(FitnessGoal goal) {
        return fitnessGoalRepository.update(goal);
    }

    // Delete a fitness goal by ID
    public int deleteFitnessGoal(Long goalId) {
        return fitnessGoalRepository.delete(goalId);
    }
}
