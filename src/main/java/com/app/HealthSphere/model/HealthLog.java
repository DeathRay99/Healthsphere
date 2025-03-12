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
    private Integer waterIntake; // Added water_intake field

    public HealthLog() {}

    public HealthLog(Long logId, Long userId, String logType, String description, Integer calories, Double protein,
                     Double carbohydrates, Double fats, Integer durationMinutes, String intensity,
                     Double weight, Double bodyFat, Integer bloodPressureSystolic, Integer bloodPressureDiastolic,
                     Integer heartRate, Integer sleep, Integer waterIntake) {
        this.logId = logId;
        this.userId = userId;
        this.logType = logType;
        this.description = description;
        this.calories = calories != null ? calories : 0;
        this.protein = protein != null ? protein : 0.0;
        this.carbohydrates = carbohydrates != null ? carbohydrates : 0.0;
        this.fats = fats != null ? fats : 0.0;
        this.durationMinutes = durationMinutes != null ? durationMinutes : 0;
        this.intensity = intensity;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.heartRate = heartRate;
        this.logDate = LocalDateTime.now();
        this.sleep = sleep != null ? sleep : 0;
        this.waterIntake = waterIntake != null ? waterIntake : 0;
    }

    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getLogType() { return logType; }
    public void setLogType(String logType) { this.logType = logType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }

    public Double getProtein() { return protein; }
    public void setProtein(Double protein) { this.protein = protein; }

    public Double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(Double carbohydrates) { this.carbohydrates = carbohydrates; }

    public Double getFats() { return fats; }
    public void setFats(Double fats) { this.fats = fats; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getBodyFat() { return bodyFat; }
    public void setBodyFat(Double bodyFat) { this.bodyFat = bodyFat; }

    public Integer getBloodPressureSystolic() { return bloodPressureSystolic; }
    public void setBloodPressureSystolic(Integer bloodPressureSystolic) { this.bloodPressureSystolic = bloodPressureSystolic; }

    public Integer getBloodPressureDiastolic() { return bloodPressureDiastolic; }
    public void setBloodPressureDiastolic(Integer bloodPressureDiastolic) { this.bloodPressureDiastolic = bloodPressureDiastolic; }

    public Integer getHeartRate() { return heartRate; }
    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }

    public LocalDateTime getLogDate() { return logDate; }
    public void setLogDate(LocalDateTime logDate) { this.logDate = logDate; }

    public Integer getSleep() { return sleep; }
    public void setSleep(Integer sleep) { this.sleep = sleep; }

    public Integer getWaterIntake() { return waterIntake; }
    public void setWaterIntake(Integer waterIntake) { this.waterIntake = waterIntake; }
}
