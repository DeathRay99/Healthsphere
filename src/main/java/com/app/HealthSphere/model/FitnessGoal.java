package com.app.HealthSphere.model;

import java.util.Date;

public class FitnessGoal {
    private Long goalId;
    private Long userId;
    private String goalType;
    private Double targetWeight;
    private Double targetBodyFat;
    private Date startDate;  // Added this field
    private Date targetDate;

    public FitnessGoal() {}

    public FitnessGoal(Long goalId, Long userId, String goalType, Double targetWeight, Double targetBodyFat, Date startDate, Date targetDate) {
        this.goalId = goalId;
        this.userId = userId;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.targetBodyFat = targetBodyFat;
        this.startDate = startDate;  // Added this parameter
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

    public Date getStartDate() { return startDate; }  // Added getter
    public void setStartDate(Date startDate) { this.startDate = startDate; }  // Added setter

    public Date getTargetDate() { return targetDate; }
    public void setTargetDate(Date targetDate) { this.targetDate = targetDate; }
}