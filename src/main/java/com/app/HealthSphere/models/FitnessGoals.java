package com.app.HealthSphere.models;

import java.util.Date;

public class FitnessGoals {
    private Long goalId;
    private Long userId;
    private String goalType;
    private Double targetWeight;
    private Double targetBodyFat;
    private Date targetDate;

    public FitnessGoals() {}

    public FitnessGoals(Long userId, String goalType, Double targetWeight, Double targetBodyFat, Date targetDate) {
        this.userId = userId;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.targetBodyFat = targetBodyFat;
        this.targetDate = targetDate;
    }

    public Long getGoalId() { return goalId; }
    public void setGoalId(Long goalId) { this.goalId = goalId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getGoalType() { return goalType; }
    public void setGoalType(String goalType) { this.goalType = goalType; }

    public Double getTargetWeight() { return targetWeight; }
    public void setTargetWeight(Double targetWeight) { this.targetWeight = targetWeight; }

    public Double getTargetBodyFat() { return targetBodyFat; }
    public void setTargetBodyFat(Double targetBodyFat) { this.targetBodyFat = targetBodyFat; }

    public Date getTargetDate() { return targetDate; }
    public void setTargetDate(Date targetDate) { this.targetDate = targetDate; }
}
