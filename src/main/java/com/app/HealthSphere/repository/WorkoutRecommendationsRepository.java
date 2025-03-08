package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.WorkoutRecommendations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;

@Repository
public class WorkoutRecommendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public WorkoutRecommendationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WorkoutRecommendations> workoutRecommendationsRowMapper = new RowMapper<WorkoutRecommendations>() {
        @Override
        public WorkoutRecommendations mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkoutRecommendations workoutRecommendation = new WorkoutRecommendations();
            workoutRecommendation.setWorkoutId(rs.getInt("workout_id"));
            workoutRecommendation.setWorkoutName(rs.getString("workout_name"));
            workoutRecommendation.setWorkoutDescription(rs.getString("workout_description"));
            workoutRecommendation.setExerciseType(rs.getString("exercise_type"));
            workoutRecommendation.setDurationMinutes(rs.getInt("duration_minutes"));
            workoutRecommendation.setCaloriesBurned(rs.getInt("calories_burned"));
            workoutRecommendation.setDifficultyLevel(rs.getString("difficulty_level"));
            workoutRecommendation.setFrequencyPerWeek(rs.getInt("frequency_per_week"));
            workoutRecommendation.setEquipmentNeeded(rs.getString("equipment_needed"));
            workoutRecommendation.setVideoUrl(rs.getString("video_url"));
            workoutRecommendation.setCreatedAt(rs.getTimestamp("created_at"));
            workoutRecommendation.setUpdatedAt(rs.getTimestamp("updated_at"));
            return workoutRecommendation;
        }
    };

    public List<WorkoutRecommendations> findAll() {
        String sql = "SELECT * FROM workout_recommendations";
        return jdbcTemplate.query(sql, workoutRecommendationsRowMapper);
    }

    public WorkoutRecommendations findById(int workoutId) {
        String sql = "SELECT * FROM workout_recommendations WHERE workout_id = ?";
        return jdbcTemplate.queryForObject(sql, workoutRecommendationsRowMapper, workoutId);
    }

    public int save(WorkoutRecommendations workoutRecommendations) {
        String sql = "INSERT INTO workout_recommendations (workout_name, workout_description, exercise_type, duration_minutes, calories_burned, difficulty_level, frequency_per_week, equipment_needed, video_url, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                workoutRecommendations.getWorkoutName(),
                workoutRecommendations.getWorkoutDescription(),
                workoutRecommendations.getExerciseType(),
                workoutRecommendations.getDurationMinutes(),
                workoutRecommendations.getCaloriesBurned(),
                workoutRecommendations.getDifficultyLevel(),
                workoutRecommendations.getFrequencyPerWeek(),
                workoutRecommendations.getEquipmentNeeded(),
                workoutRecommendations.getVideoUrl(),
                workoutRecommendations.getCreatedAt(),
                workoutRecommendations.getUpdatedAt());
    }

    public int update(WorkoutRecommendations workoutRecommendations) {
        StringBuilder sql = new StringBuilder("UPDATE workout_recommendations SET ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (workoutRecommendations.getWorkoutName() != null) {
            sql.append("workout_name = :workoutName, ");
            params.addValue("workoutName", workoutRecommendations.getWorkoutName());
        }
        if (workoutRecommendations.getWorkoutDescription() != null) {
            sql.append("workout_description = :workoutDescription, ");
            params.addValue("workoutDescription", workoutRecommendations.getWorkoutDescription());
        }
        if (workoutRecommendations.getExerciseType() != null) {
            sql.append("exercise_type = :exerciseType, ");
            params.addValue("exerciseType", workoutRecommendations.getExerciseType());
        }
        if (workoutRecommendations.getDurationMinutes() != 0) {
            sql.append("duration_minutes = :durationMinutes, ");
            params.addValue("durationMinutes", workoutRecommendations.getDurationMinutes());
        }
        if (workoutRecommendations.getCaloriesBurned() != 0) {
            sql.append("calories_burned = :caloriesBurned, ");
            params.addValue("caloriesBurned", workoutRecommendations.getCaloriesBurned());
        }
        if (workoutRecommendations.getDifficultyLevel() != null) {
            sql.append("difficulty_level = :difficultyLevel, ");
            params.addValue("difficultyLevel", workoutRecommendations.getDifficultyLevel());
        }
        if (workoutRecommendations.getFrequencyPerWeek() != 0) {
            sql.append("frequency_per_week = :frequencyPerWeek, ");
            params.addValue("frequencyPerWeek", workoutRecommendations.getFrequencyPerWeek());
        }
        if (workoutRecommendations.getEquipmentNeeded() != null) {
            sql.append("equipment_needed = :equipmentNeeded, ");
            params.addValue("equipmentNeeded", workoutRecommendations.getEquipmentNeeded());
        }
        if (workoutRecommendations.getVideoUrl() != null) {
            sql.append("video_url = :videoUrl, ");
            params.addValue("videoUrl", workoutRecommendations.getVideoUrl());
        }
        if (workoutRecommendations.getCreatedAt() != null) {
            sql.append("created_at = :createdAt, ");
            params.addValue("createdAt", workoutRecommendations.getCreatedAt());
        }
        if (workoutRecommendations.getUpdatedAt() != null) {
            sql.append("updated_at = :updatedAt, ");
            params.addValue("updatedAt", workoutRecommendations.getUpdatedAt());
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE workout_id = :workoutId");
        params.addValue("workoutId", workoutRecommendations.getWorkoutId());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    public int deleteById(int workoutId) {
        String sql = "DELETE FROM workout_recommendations WHERE workout_id = ?";
        return jdbcTemplate.update(sql, workoutId);
    }
}
