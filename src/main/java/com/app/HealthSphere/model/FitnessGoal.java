package com.app.HealthSphere.model;

import java.util.Date;

public class FitnessGoal {
    private Long goalId;
    private Long userId;
    private String goalType;
    private Double targetWeight;
    private Double targetBodyFat;
    private Date targetDate;

    // Default Constructor
    public FitnessGoal() {}

    // Parameterized Constructor
    public FitnessGoal(Long goalId, Long userId, String goalType, Double targetWeight, Double targetBodyFat, Date targetDate) {
        this.goalId = goalId;
        this.userId = userId;
        this.goalType = goalType;
        this.targetWeight = targetWeight;
        this.targetBodyFat = targetBodyFat;
        this.targetDate = targetDate;

        // Perform field validation
        validateFields();
    }

    // Getters and Setters with Validation
    public Long getGoalId() { return goalId; }
    public void setGoalId(Long goalId) { this.goalId = goalId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid userId: It must be a positive number.");
        }
        this.userId = userId;
    }

    public String getGoalType() { return goalType; }
    public void setGoalType(String goalType) {
        if (goalType == null || goalType.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid goalType: It cannot be null or empty.");
        }
        this.goalType = goalType;
    }

    public Double getTargetWeight() { return targetWeight; }
    public void setTargetWeight(Double targetWeight) {
        if (targetWeight == null || targetWeight <= 0) {
            throw new IllegalArgumentException("Invalid targetWeight: It must be a positive number.");
        }
        this.targetWeight = targetWeight;
    }

    public Double getTargetBodyFat() { return targetBodyFat; }
    public void setTargetBodyFat(Double targetBodyFat) {
        if (targetBodyFat != null && (targetBodyFat < 0 || targetBodyFat > 100)) {
            throw new IllegalArgumentException("Invalid targetBodyFat: It must be between 0 and 100%.");
        }
        this.targetBodyFat = targetBodyFat;
    }

    public Date getTargetDate() { return targetDate; }
    public void setTargetDate(Date targetDate) {
        if (targetDate == null || targetDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid targetDate: It must be a future date.");
        }
        this.targetDate = targetDate;
    }

    // Method to validate fields
    private void validateFields() {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }

        if (goalType == null || goalType.trim().isEmpty()) {
            throw new IllegalArgumentException("Goal Type cannot be null or empty.");
        }

        if (targetWeight == null || targetWeight <= 0) {
            throw new IllegalArgumentException("Target Weight must be a positive number.");
        }

        if (targetBodyFat != null && (targetBodyFat < 0 || targetBodyFat > 100)) {
            throw new IllegalArgumentException("Target Body Fat must be between 0 and 100%.");
        }

        if (targetDate == null || targetDate.before(new Date())) {
            throw new IllegalArgumentException("Target Date must be a valid future date.");
        }
    }

    // Debugging Utility (Optional)
    @Override
    public String toString() {
        return "FitnessGoal{" +
                "goalId=" + goalId +
                ", userId=" + userId +
                ", goalType='" + goalType + '\'' +
                ", targetWeight=" + targetWeight +
                ", targetBodyFat=" + targetBodyFat +
                ", targetDate=" + targetDate +
                '}';
    }
}
