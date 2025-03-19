package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.WorkoutRecommendations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class WorkoutRecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public WorkoutRecommendationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WorkoutRecommendations> rowMapper = (ResultSet rs, int rowNum) ->
            new WorkoutRecommendations(
                    rs.getInt("workout_id"),
                    rs.getInt("user_id"),
                    rs.getInt("goal_id"),
                    rs.getString("workout_name"),
                    rs.getString("workout_description"),
                    rs.getString("exercise_type"),
                    rs.getInt("duration_minutes"),
                    rs.getInt("calories_burned"),
                    rs.getString("difficulty_level"),
                    rs.getInt("frequency_per_week"),
                    rs.getString("equipment_needed"),
                    rs.getString("video_url"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            );

    public List<WorkoutRecommendations> findAll() {
        String sql = "SELECT * FROM WorkoutRecommendations";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public WorkoutRecommendations findById(int workoutId) {
        String sql = "SELECT * FROM WorkoutRecommendations WHERE workout_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, workoutId);
    }

    public int save(WorkoutRecommendations workoutRecommendations) {
        String sql = "INSERT INTO WorkoutRecommendations (user_id, goal_id, workout_name, workout_description, exercise_type, duration_minutes, calories_burned, difficulty_level, frequency_per_week, equipment_needed, video_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                workoutRecommendations.getUserId(),
                workoutRecommendations.getGoalId(),
                workoutRecommendations.getWorkoutName(),
                workoutRecommendations.getWorkoutDescription(),
                workoutRecommendations.getExerciseType(),
                workoutRecommendations.getDurationMinutes(),
                workoutRecommendations.getCaloriesBurned(),
                workoutRecommendations.getDifficultyLevel(),
                workoutRecommendations.getFrequencyPerWeek(),
                workoutRecommendations.getEquipmentNeeded(),
                workoutRecommendations.getVideoUrl());
    }

    public int update(WorkoutRecommendations workout) {
        String sql = "UPDATE WorkoutRecommendations SET user_id = ?, goal_id = ?, workout_name = ?, workout_description = ?, " +
                "exercise_type = ?, duration_minutes = ?, calories_burned = ?, difficulty_level = ?, frequency_per_week = ?, " +
                "equipment_needed = ?, video_url = ?, created_at = ?, updated_at = ? WHERE workout_id = ?";
        return jdbcTemplate.update(sql,
                workout.getUserId(),
                workout.getGoalId(),
                workout.getWorkoutName(),
                workout.getWorkoutDescription(),
                workout.getExerciseType(),
                workout.getDurationMinutes(),
                workout.getCaloriesBurned(),
                workout.getDifficultyLevel(),
                workout.getFrequencyPerWeek(),
                workout.getEquipmentNeeded(),
                workout.getVideoUrl(),
                workout.getCreatedAt(),
                workout.getUpdatedAt(),
                workout.getWorkoutId()
        );
    }

    public int deleteById(int workoutId) {
        String sql = "DELETE FROM WorkoutRecommendations WHERE workout_id = ?";
        return jdbcTemplate.update(sql, workoutId);
    }
}
