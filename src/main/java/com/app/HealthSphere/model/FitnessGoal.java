package com.app.HealthSphere.model;

import java.util.Date;

public class FitnessGoal {
    private Long goalId;
    private Long userId;
    private String goalType;
    private Double targetWeight;
    private Double targetBodyFat;
    private Date startDate;
    private Date targetDate;

    public FitnessGoal() {}

    public FitnessGoal(Long goalId, Long userId, String goalType, Double targetWeight, Double targetBodyFat, Date startDate, Date targetDate) {
        if (targetWeight == null || targetWeight <= 0) {
            throw new IllegalArgumentException("Target weight must be greater than zero.");
        }
        if (targetBodyFat == null || targetBodyFat <= 0) {
            throw new IllegalArgumentException("Target body fat percentage must be greater than zero.");
        }
        if (startDate != null && targetDate != null && targetDate.before(startDate)) {
            throw new IllegalArgumentException("Target date cannot be before start date.");
        }

        this.goalId = goalId;
        this.userId = userId;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.targetBodyFat = targetBodyFat;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    public Long getGoalId() { return goalId; }
    public void setGoalId(Long goalId) { this.goalId = goalId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getGoalType() { return goalType; }
    public void setGoalType(String goalType) { this.goalType = goalType; }

    public Double getTargetWeight() { return targetWeight; }
    public void setTargetWeight(Double targetWeight) {
        if (targetWeight == null || targetWeight <= 0) {
            throw new IllegalArgumentException("Target weight must be greater than zero.");
        }
        this.targetWeight = targetWeight;
    }

    public Double getTargetBodyFat() { return targetBodyFat; }
    public void setTargetBodyFat(Double targetBodyFat) {
        if (targetBodyFat == null || targetBodyFat <= 0) {
            throw new IllegalArgumentException("Target body fat percentage must be greater than zero.");
        }
        this.targetBodyFat = targetBodyFat;
    }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getTargetDate() { return targetDate; }
    public void setTargetDate(Date targetDate) {
        if (startDate != null && targetDate != null && targetDate.before(startDate)) {
            throw new IllegalArgumentException("Target date cannot be before start date.");
        }
        this.targetDate = targetDate;
    }
}
