package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.FitnessGoal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FitnessGoalRepository {

    private final JdbcTemplate jdbcTemplate;

    public FitnessGoalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for FitnessGoal
    private final RowMapper<FitnessGoal> fitnessGoalRowMapper = (rs, rowNum) -> new FitnessGoal(
            rs.getLong("goal_id"),
            rs.getLong("user_id"),
            rs.getString("goal_type"),
            rs.getDouble("target_weight"),
            rs.getDouble("target_body_fat"),
            rs.getDate("target_date"));

    // Save a new fitness goal
    public int save(FitnessGoal goal) {
        String sql = "INSERT INTO FitnessGoals (user_id, goal_type, target_weight, target_body_fat, target_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, goal.getUserId(), goal.getGoalType(), goal.getTargetWeight(),
                goal.getTargetBodyFat(), goal.getTargetDate());
    }

    // Retrieve all fitness goals
    public List<FitnessGoal> findAll() {
        String sql = "SELECT * FROM FitnessGoals";
        return jdbcTemplate.query(sql, fitnessGoalRowMapper);
    }

    // Find a fitness goal by ID
    public FitnessGoal findById(Long goalId) {
        String sql = "SELECT * FROM FitnessGoals WHERE goal_id = ?";
        return jdbcTemplate.queryForObject(sql, fitnessGoalRowMapper, goalId);
    }

    // Update an existing fitness goal

    public int update(FitnessGoal goal) {
        StringBuilder sql = new StringBuilder("UPDATE FitnessGoals SET ");
        Map<String, Object> params = new HashMap<>();

        if (goal.getGoalType() != null) {
            sql.append("goal_type = :goalType, ");
            params.put("goalType", goal.getGoalType());
        }
        if (goal.getTargetWeight() != null) {
            sql.append("target_weight = :targetWeight, ");
            params.put("targetWeight", goal.getTargetWeight());
        }
        if (goal.getTargetBodyFat() != null) {
            sql.append("target_body_fat = :targetBodyFat, ");
            params.put("targetBodyFat", goal.getTargetBodyFat());
        }
        if (goal.getTargetDate() != null) {
            sql.append("target_date = :targetDate, ");
            params.put("targetDate", goal.getTargetDate());
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE goal_id = :goalId");
        params.put("goalId", goal.getGoalId());

        return jdbcTemplate.update(sql.toString(), params);
    }

    // Delete a fitness goal by ID
    public int delete(Long goalId) {
        String sql = "DELETE FROM FitnessGoals WHERE goal_id = ?";
        return jdbcTemplate.update(sql, goalId);
    }
}
