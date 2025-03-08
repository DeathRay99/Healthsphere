package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.HealthLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HealthLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public HealthLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for HealthLog
    private final RowMapper<HealthLog> healthLogRowMapper = (rs, rowNum) ->
            new HealthLog(
                    rs.getLong("log_id"),
                    rs.getLong("user_id"),
                    rs.getString("log_type"),
                    rs.getString("description"),
                    rs.getInt("calories"),
                    rs.getDouble("protein"),
                    rs.getDouble("carbohydrates"),
                    rs.getDouble("fats"),
                    rs.getInt("duration_minutes"),
                    rs.getString("intensity"),
                    rs.getDouble("weight"),
                    rs.getDouble("body_fat"),
                    rs.getInt("blood_pressure_systolic"),
                    rs.getInt("blood_pressure_diastolic"),
                    rs.getInt("heart_rate")
            );

    // Create
    public int save(HealthLog healthLog) {
        String sql = "INSERT INTO HealthLogs (user_id, log_type, description, calories, protein, carbohydrates, fats, duration_minutes, intensity, weight, body_fat, blood_pressure_systolic, blood_pressure_diastolic, heart_rate, log_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                healthLog.getUserId(),
                healthLog.getLogType(),
                healthLog.getDescription(),
                healthLog.getCalories(),
                healthLog.getProtein(),
                healthLog.getCarbohydrates(),
                healthLog.getFats(),
                healthLog.getDurationMinutes(),
                healthLog.getIntensity(),
                healthLog.getWeight(),
                healthLog.getBodyFat(),
                healthLog.getBloodPressureSystolic(),
                healthLog.getBloodPressureDiastolic(),
                healthLog.getHeartRate(),
                healthLog.getLogDate()
        );
    }

    // Read (Find all logs)
    public List<HealthLog> findAll() {
        String sql = "SELECT * FROM HealthLogs";
        return jdbcTemplate.query(sql, healthLogRowMapper);
    }

    // Read (Find by ID)
    public HealthLog findById(Long logId) {
        String sql = "SELECT * FROM HealthLogs WHERE log_id = ?";
        return jdbcTemplate.queryForObject(sql, healthLogRowMapper, logId);
    }

    // Update
    public int update(HealthLog healthLog) {
        String sql = "UPDATE HealthLogs SET log_type = ?, description = ?, calories = ?, protein = ?, carbohydrates = ?, fats = ?, duration_minutes = ?, intensity = ?, weight = ?, body_fat = ?, blood_pressure_systolic = ?, blood_pressure_diastolic = ?, heart_rate = ? WHERE log_id = ?";
        return jdbcTemplate.update(sql,
                healthLog.getLogType(),
                healthLog.getDescription(),
                healthLog.getCalories(),
                healthLog.getProtein(),
                healthLog.getCarbohydrates(),
                healthLog.getFats(),
                healthLog.getDurationMinutes(),
                healthLog.getIntensity(),
                healthLog.getWeight(),
                healthLog.getBodyFat(),
                healthLog.getBloodPressureSystolic(),
                healthLog.getBloodPressureDiastolic(),
                healthLog.getHeartRate(),
                healthLog.getLogId()
        );
    }

    // Delete
    public int delete(Long logId) {
        String sql = "DELETE FROM HealthLogs WHERE log_id = ?";
        return jdbcTemplate.update(sql, logId);
    }
}
