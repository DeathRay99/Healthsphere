package com.app.HealthSphere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DietRecommendations {
    private int dietId;
    private String dietName;
    private String dietDescription;
    private int caloriesPerDay;
    private BigDecimal proteinPercentage;
    private BigDecimal carbsPercentage;
    private BigDecimal fatPercentage;
    private int mealFrequency;
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
    public DietRecommendations(int dietId, String dietName, String dietDescription, int caloriesPerDay,
            BigDecimal proteinPercentage, BigDecimal carbsPercentage, BigDecimal fatPercentage, int mealFrequency,
            String hydrationRecommendation, String foodsToInclude, String foodsToAvoid, String supplementsRecommended,
            Timestamp createdAt, Timestamp updatedAt) {
        this.dietId = dietId;
        this.dietName = dietName;
        this.dietDescription = dietDescription;
        this.caloriesPerDay = caloriesPerDay;
        this.proteinPercentage = proteinPercentage;
        this.carbsPercentage = carbsPercentage;
        this.fatPercentage = fatPercentage;
        this.mealFrequency = mealFrequency;
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
        this.caloriesPerDay = caloriesPerDay;
    }

    public BigDecimal getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(BigDecimal proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public BigDecimal getCarbsPercentage() {
        return carbsPercentage;
    }

    public void setCarbsPercentage(BigDecimal carbsPercentage) {
        this.carbsPercentage = carbsPercentage;
    }

    public BigDecimal getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(BigDecimal fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public int getMealFrequency() {
        return mealFrequency;
    }

    public void setMealFrequency(int mealFrequency) {
        this.mealFrequency = mealFrequency;
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
