package com.app.HealthSphere.model;

import java.time.LocalDateTime;

public class HealthLog {
    private Long logId;
    private Long userId;
    private String logType;
    private String description;
    private Integer calories;
    private Double protein;
    private Double carbohydrates;
    private Double fats;
    private Integer durationMinutes;
    private String intensity;
    private Double weight;
    private Double bodyFat;
    private Integer bloodPressureSystolic;
    private Integer bloodPressureDiastolic;
    private Integer heartRate;
    private LocalDateTime logDate;
    private Integer sleep; // Added sleep field
    private Integer waterIntake; // Added waterIntake field

    // Constructors
    public HealthLog() {
        this.logDate = LocalDateTime.now();
        this.calories = 0;
        this.sleep = 0;
        this.waterIntake = 0;
    }

    public HealthLog(Long logId, Long userId, String logType, String description, Integer calories, Double protein,
                     Double carbohydrates, Double fats, Integer durationMinutes, String intensity,
                     Double weight, Double bodyFat, Integer bloodPressureSystolic, Integer bloodPressureDiastolic,
                     Integer heartRate, Integer sleep, Integer waterIntake) {
        try {
            setLogId(logId);
            setUserId(userId);
            setLogType(logType);
            setDescription(description);
            setCalories((calories != null) ? calories : 0);
            setProtein((protein != null) ? protein : 0.0);
            setCarbohydrates((carbohydrates != null) ? carbohydrates : 0.0);
            setFats((fats != null) ? fats : 0.0);
            setDurationMinutes((durationMinutes != null) ? durationMinutes : 0);
            setIntensity(intensity);
            setWeight(weight);
            setBodyFat(bodyFat);
            setBloodPressureSystolic(bloodPressureSystolic);
            setBloodPressureDiastolic(bloodPressureDiastolic);
            setHeartRate(heartRate);
            this.logDate = LocalDateTime.now();
            setSleep((sleep != null) ? sleep : 0);
            setWaterIntake((waterIntake != null) ? waterIntake : 0);
        } catch (Exception e) {
            System.err.println("Error occurred while initializing HealthLog: " + e.getMessage());
            throw e;
        }
    }

    // Getters and Setters with Exception Handling
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        if (logId == null || logId <= 0) {
            throw new IllegalArgumentException("Log ID must be a positive number.");
        }
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
        this.userId = userId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        if (logType == null || logType.trim().isEmpty()) {
            throw new IllegalArgumentException("Log type cannot be null or empty.");
        }
        this.logType = logType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        if (calories != null && calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative.");
        }
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        if (durationMinutes != null && durationMinutes < 0) {
            throw new IllegalArgumentException("Duration in minutes cannot be negative.");
        }
        this.durationMinutes = durationMinutes;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(Double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Integer getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    public void setBloodPressureSystolic(Integer bloodPressureSystolic) {
        this.bloodPressureSystolic = bloodPressureSystolic;
    }

    public Integer getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }

    public void setBloodPressureDiastolic(Integer bloodPressureDiastolic) {
        this.bloodPressureDiastolic = bloodPressureDiastolic;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        if (logDate != null && logDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Log date cannot be in the future.");
        }
        this.logDate = logDate;
    }

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        if (sleep != null && sleep < 0) {
            throw new IllegalArgumentException("Sleep hours cannot be negative.");
        }
        this.sleep = sleep;
    }

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Integer waterIntake) {
        if (waterIntake != null && waterIntake < 0) {
            throw new IllegalArgumentException("Water intake cannot be negative.");
        }
        this.waterIntake = waterIntake;
    }
}
