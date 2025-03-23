//package com.app.HealthSphere.repository;
//
//import com.app.HealthSphere.model.HealthLog;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class HealthLogRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public HealthLogRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    // RowMapper for HealthLog
//    private final RowMapper<HealthLog> healthLogRowMapper = (rs, rowNum) -> new HealthLog(
//            rs.getLong("log_id"),
//            rs.getLong("user_id"),
//            rs.getString("log_type"),
//            rs.getString("description"),
//            rs.getInt("calories"),
//            rs.getDouble("protein"),
//            rs.getDouble("carbohydrates"),
//            rs.getDouble("fats"),
//            rs.getInt("duration_minutes"),
//            rs.getString("intensity"),
//            rs.getDouble("weight"),
//            rs.getDouble("body_fat"),
//            rs.getInt("blood_pressure_systolic"),
//            rs.getInt("blood_pressure_diastolic"),
//            rs.getInt("heart_rate"),
//            rs.getInt("sleep"), // Added sleep field
//            rs.getInt("water_intake")); // Added water_intake field
//
//    // Create
//    public int save(HealthLog healthLog) {
//        String sql = "INSERT INTO HealthLogs (user_id, log_type, description, calories, protein, carbohydrates, fats, duration_minutes, intensity, weight, body_fat, blood_pressure_systolic, blood_pressure_diastolic, heart_rate, log_date, sleep, water_intake) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql,
//                healthLog.getUserId(),
//                healthLog.getLogType(),
//                healthLog.getDescription(),
//                healthLog.getCalories(),
//                healthLog.getProtein(),
//                healthLog.getCarbohydrates(),
//                healthLog.getFats(),
//                healthLog.getDurationMinutes(),
//                healthLog.getIntensity(),
//                healthLog.getWeight(),
//                healthLog.getBodyFat(),
//                healthLog.getBloodPressureSystolic(),
//                healthLog.getBloodPressureDiastolic(),
//                healthLog.getHeartRate(),
//                healthLog.getLogDate(),
//                healthLog.getSleep(), // Added sleep parameter
//                healthLog.getWaterIntake()); // Added water_intake parameter
//    }
//
//    // Read (Find all logs)
//    public List<HealthLog> findAll() {
//        String sql = "SELECT * FROM HealthLogs";
//        return jdbcTemplate.query(sql, healthLogRowMapper);
//    }
//
//    // Read (Find by ID)
//    public HealthLog findById(Long logId) {
//        String sql = "SELECT * FROM HealthLogs WHERE log_id = ?";
//        return jdbcTemplate.queryForObject(sql, healthLogRowMapper, logId);
//    }
//
//    // Update
//    public int update(HealthLog healthLog) {
//        StringBuilder sql = new StringBuilder("UPDATE HealthLogs SET ");
//        Map<String, Object> params = new HashMap<>();
//
//        if (healthLog.getLogType() != null) {
//            sql.append("log_type = ?, ");
//            params.put("logType", healthLog.getLogType());
//        }
//        if (healthLog.getDescription() != null) {
//            sql.append("description = ?, ");
//            params.put("description", healthLog.getDescription());
//        }
//        if (healthLog.getCalories() != null) {
//            sql.append("calories = ?, ");
//            params.put("calories", healthLog.getCalories());
//        }
//        if (healthLog.getProtein() != null) {
//            sql.append("protein = ?, ");
//            params.put("protein", healthLog.getProtein());
//        }
//        if (healthLog.getCarbohydrates() != null) {
//            sql.append("carbohydrates = ?, ");
//            params.put("carbohydrates", healthLog.getCarbohydrates());
//        }
//        if (healthLog.getFats() != null) {
//            sql.append("fats = ?, ");
//            params.put("fats", healthLog.getFats());
//        }
//        if (healthLog.getDurationMinutes() != null) {
//            sql.append("duration_minutes = ?, ");
//            params.put("durationMinutes", healthLog.getDurationMinutes());
//        }
//        if (healthLog.getIntensity() != null) {
//            sql.append("intensity = ?, ");
//            params.put("intensity", healthLog.getIntensity());
//        }
//        if (healthLog.getWeight() != null) {
//            sql.append("weight = ?, ");
//            params.put("weight", healthLog.getWeight());
//        }
//        if (healthLog.getBodyFat() != null) {
//            sql.append("body_fat = ?, ");
//            params.put("bodyFat", healthLog.getBodyFat());
//        }
//        if (healthLog.getBloodPressureSystolic() != null) {
//            sql.append("blood_pressure_systolic = ?, ");
//            params.put("bloodPressureSystolic", healthLog.getBloodPressureSystolic());
//        }
//        if (healthLog.getBloodPressureDiastolic() != null) {
//            sql.append("blood_pressure_diastolic = ?, ");
//            params.put("bloodPressureDiastolic", healthLog.getBloodPressureDiastolic());
//        }
//        if (healthLog.getHeartRate() != null) {
//            sql.append("heart_rate = ?, ");
//            params.put("heartRate", healthLog.getHeartRate());
//        }
//        if (healthLog.getSleep() != null) { // Add sleep update condition
//            sql.append("sleep = ?, ");
//            params.put("sleep", healthLog.getSleep());
//        }
//        if (healthLog.getWaterIntake() != null) { // Add water_intake update condition
//            sql.append("water_intake = ?, ");
//            params.put("waterIntake", healthLog.getWaterIntake());
//        }
//
//        // Remove the last comma and space
//        sql.setLength(sql.length() - 2);
//
//        sql.append(" WHERE log_id = ?");
//        params.put("logId", healthLog.getLogId());
//
//        return jdbcTemplate.update(sql.toString(),
//                params.values().toArray());
//    }
//
//    // Delete
//    public int delete(Long logId) {
//        String sql = "DELETE FROM HealthLogs WHERE log_id = ?";
//        return jdbcTemplate.update(sql, logId);
//    }
//}
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
    private final RowMapper<HealthLog> healthLogRowMapper = (rs, rowNum) -> new HealthLog(
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
            rs.getInt("heart_rate"),
            rs.getInt("sleep"),
            rs.getInt("water_intake"));

    // Create
    public int save(HealthLog healthLog) {
        String sql = "INSERT INTO HealthLogs (user_id, log_type, description, calories, protein, carbohydrates, fats, duration_minutes, intensity, weight, body_fat, blood_pressure_systolic, blood_pressure_diastolic, heart_rate, log_date, sleep, water_intake) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                healthLog.getLogDate(),
                healthLog.getSleep(),
                healthLog.getWaterIntake());
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

    // NEW METHOD: Find by User ID
    public List<HealthLog> findByUserId(Long userId) {
        String sql = "SELECT * FROM HealthLogs WHERE user_id = ?";
        return jdbcTemplate.query(sql, healthLogRowMapper, userId);
    }

    // Update
    public int update(HealthLog healthLog) {
        String sql = "UPDATE HealthLogs SET log_type = ?, description = ?, calories = ?, protein = ?, carbohydrates = ?, fats = ?, duration_minutes = ?, intensity = ?, weight = ?, body_fat = ?, blood_pressure_systolic = ?, blood_pressure_diastolic = ?, heart_rate = ?, sleep = ?, water_intake = ? WHERE log_id = ?";
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
                healthLog.getSleep(),
                healthLog.getWaterIntake(),
                healthLog.getLogId());
    }

    // Delete
    public int delete(Long logId) {
        String sql = "DELETE FROM HealthLogs WHERE log_id = ?";
        return jdbcTemplate.update(sql, logId);
    }
}
