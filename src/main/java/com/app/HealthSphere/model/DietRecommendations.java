package com.app.HealthSphere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DietRecommendations {
    private int dietId;
    private int userId;
    private int goalId;
    private String dietName;
    private String dietDescription;
    private int caloriesPerDay;
    private BigDecimal proteinPercentage;
    private BigDecimal carbsPercentage;
    private BigDecimal fatPercentage;
    private String mealType;
    private String hydrationRecommendation;
    private String foodsToInclude;
    private String foodsToAvoid;
    private String supplementsRecommended;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default constructor
    public DietRecommendations() {
    }

    // Parameterized constructor
    public DietRecommendations(int dietId, int userId, int goalId, String dietName, String dietDescription,
                               int caloriesPerDay, BigDecimal proteinPercentage, BigDecimal carbsPercentage,
                               BigDecimal fatPercentage, String mealType, String hydrationRecommendation,
                               String foodsToInclude, String foodsToAvoid, String supplementsRecommended,
                               Timestamp createdAt, Timestamp updatedAt) {
        this.dietId = dietId;
        this.userId = userId;
        this.goalId = goalId;
        this.dietName = dietName;
        this.dietDescription = dietDescription;
        this.setCaloriesPerDay(caloriesPerDay); // Use setter to validate
        this.setProteinPercentage(proteinPercentage); // Use setter to validate
        this.setCarbsPercentage(carbsPercentage); // Use setter to validate
        this.setFatPercentage(fatPercentage); // Use setter to validate
        this.mealType = mealType;
        this.hydrationRecommendation = hydrationRecommendation;
        this.foodsToInclude = foodsToInclude;
        this.foodsToAvoid = foodsToAvoid;
        this.supplementsRecommended = supplementsRecommended;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietDescription() {
        return dietDescription;
    }

    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        if (caloriesPerDay < 0) {
            throw new IllegalArgumentException("Calories per day cannot be negative.");
        }
        this.caloriesPerDay = caloriesPerDay;
    }

    public BigDecimal getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(BigDecimal proteinPercentage) {
        if (proteinPercentage == null || proteinPercentage.compareTo(BigDecimal.ZERO) < 0 || proteinPercentage.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Protein percentage must be between 0 and 1.");
        }
        this.proteinPercentage = proteinPercentage;
    }

    public BigDecimal getCarbsPercentage() {
        return carbsPercentage;
    }

    public void setCarbsPercentage(BigDecimal carbsPercentage) {
        if (carbsPercentage == null || carbsPercentage.compareTo(BigDecimal.ZERO) < 0 || carbsPercentage.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Carbs percentage must be between 0 and 1.");
        }
        this.carbsPercentage = carbsPercentage;
    }

    public BigDecimal getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(BigDecimal fatPercentage) {
        if (fatPercentage == null) {
            throw new IllegalArgumentException("Fat percentage cannot be null.");
        }
        if (fatPercentage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Fat percentage cannot be negative.");
        }
        if (fatPercentage.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Fat percentage cannot exceed 100%.");
        }
        this.fatPercentage = fatPercentage;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getHydrationRecommendation() {
        return hydrationRecommendation;
    }

    public void setHydrationRecommendation(String hydrationRecommendation) {
        this.hydrationRecommendation = hydrationRecommendation;
    }

    public String getFoodsToInclude() {
        return foodsToInclude;
    }

    public void setFoodsToInclude(String foodsToInclude) {
        this.foodsToInclude = foodsToInclude;
    }

    public String getFoodsToAvoid() {
        return foodsToAvoid;
    }

    public void setFoodsToAvoid(String foodsToAvoid) {
        this.foodsToAvoid = foodsToAvoid;
    }

    public String getSupplementsRecommended() {
        return supplementsRecommended;
    }

    public void setSupplementsRecommended(String supplementsRecommended) {
        this.supplementsRecommended = supplementsRecommended;
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
