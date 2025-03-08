package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.FitnessGoal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
                    rs.getDate("target_date")
            );

    // Save a new fitness goal
    public int save(FitnessGoal goal) {
        String sql = "INSERT INTO FitnessGoals (user_id, goal_type, target_weight, target_body_fat, target_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, goal.getUserId(), goal.getGoalType(), goal.getTargetWeight(), goal.getTargetBodyFat(), goal.getTargetDate());
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

    public int updateFitnessGoal(Long goalId, FitnessGoal updatedGoal) {
        StringBuilder sql = new StringBuilder("UPDATE FitnessGoals SET ");
        List<Object> params = new ArrayList<>();

        if (updatedGoal.getGoalType() != null) {
            sql.append("goal_type = ?, ");
            params.add(updatedGoal.getGoalType());
        }
        if (updatedGoal.getTargetWeight() != null) {
            sql.append("target_weight = ?, ");
            params.add(updatedGoal.getTargetWeight());
        }
        if (updatedGoal.getTargetBodyFat() != null) {
            sql.append("target_body_fat = ?, ");
            params.add(updatedGoal.getTargetBodyFat());
        }
        if (updatedGoal.getTargetDate() != null) {
            sql.append("target_date = ?, ");
            params.add(updatedGoal.getTargetDate());
        }

        // Remove the trailing comma and space
        if (params.isEmpty()) {
            return 0; // No update needed
        }

        sql.setLength(sql.length() - 2); // Trim last comma
        sql.append(" WHERE goal_id = ?");
        params.add(goalId);

        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    // Delete a fitness goal by ID
    public int delete(Long goalId) {
        String sql = "DELETE FROM FitnessGoals WHERE goal_id = ?";
        return jdbcTemplate.update(sql, goalId);
    }
}
