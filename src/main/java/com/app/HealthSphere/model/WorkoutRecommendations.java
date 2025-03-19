package com.app.HealthSphere.model;

import java.sql.Timestamp;

public class WorkoutRecommendations {
    private int workoutId;
    private int userId;
    private Integer goalId;
    private String workoutName;
    private String workoutDescription;
    private String exerciseType;
    private int durationMinutes;
    private int caloriesBurned;
    private String difficultyLevel;
    private int frequencyPerWeek;
    private String equipmentNeeded;
    private String videoUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default constructor
    public WorkoutRecommendations() {
    }

    // Parameterized constructor
    public WorkoutRecommendations(int workoutId, int userId, Integer goalId, String workoutName,
                                  String workoutDescription, String exerciseType, int durationMinutes,
                                  int caloriesBurned, String difficultyLevel, int frequencyPerWeek,
                                  String equipmentNeeded, String videoUrl, Timestamp createdAt, Timestamp updatedAt) {
        this.workoutId = workoutId;
        this.userId = userId;
        this.goalId = goalId;
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.exerciseType = exerciseType;
        this.durationMinutes = durationMinutes;
        this.caloriesBurned = caloriesBurned;
        this.difficultyLevel = difficultyLevel;
        this.frequencyPerWeek = frequencyPerWeek;
        this.equipmentNeeded = equipmentNeeded;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getFrequencyPerWeek() {
        return frequencyPerWeek;
    }

    public void setFrequencyPerWeek(int frequencyPerWeek) {
        this.frequencyPerWeek = frequencyPerWeek;
    }

    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
