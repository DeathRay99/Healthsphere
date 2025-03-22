package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.FitnessGoal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FitnessGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    public FitnessGoalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for FitnessGoal
    private final RowMapper<FitnessGoal> fitnessGoalRowMapper = (rs, rowNum) ->
            new FitnessGoal(
                    rs.getLong("goal_id"),
                    rs.getLong("user_id"),
                    rs.getString("goal_type"),
                    rs.getDouble("target_weight"),
                    rs.getDouble("target_body_fat"),
                    rs.getDate("start_date"),  // Added this field
                    rs.getDate("target_date")
            );

    // Save a new fitness goal
    public int save(FitnessGoal goal) {
        String sql = "INSERT INTO FitnessGoals (user_id, goal_type, target_weight, target_body_fat, start_date, target_date) VALUES (?, ?, ?, ?, ?, ?) ";
        return jdbcTemplate.update(sql,
                goal.getUserId(),
                goal.getGoalType(),
                goal.getTargetWeight(),
                goal.getTargetBodyFat(),
                goal.getStartDate(),  // Added this field
                goal.getTargetDate());
    }

    // Retrieve all fitness goals
    public List<FitnessGoal> findAll() {
        String sql = "SELECT * FROM FitnessGoals";
        return jdbcTemplate.query(sql, fitnessGoalRowMapper);
    }

    // Retrieve all fitness goals of a particular user
    public List<FitnessGoal> findByUserId(Long userId) {
        String sql = "SELECT * FROM FitnessGoals WHERE user_id = ?";
        return jdbcTemplate.query(sql, fitnessGoalRowMapper, userId);
    }

    // Find a fitness goal by ID
    public FitnessGoal findById(Long goalId) {
        String sql = "SELECT * FROM FitnessGoals WHERE goal_id = ?";
        return jdbcTemplate.queryForObject(sql, fitnessGoalRowMapper, goalId);
    }

    // Update an existing fitness goal
    public int update(FitnessGoal goal) {
        String sql = "UPDATE FitnessGoals SET goal_type = ?, target_weight = ?, target_body_fat = ?, start_date = ?, target_date = ? WHERE goal_id = ?";
        return jdbcTemplate.update(sql,
                goal.getGoalType(),
                goal.getTargetWeight(),
                goal.getTargetBodyFat(),
                goal.getStartDate(),  // Added this field
                goal.getTargetDate(),
                goal.getGoalId());
    }

    // Delete a fitness goal by ID
    public int delete(Long goalId) {
        String sql = "DELETE FROM FitnessGoals WHERE goal_id = ?";
        return jdbcTemplate.update(sql, goalId);
    }


}