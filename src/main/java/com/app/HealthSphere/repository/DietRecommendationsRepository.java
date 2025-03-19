package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.DietRecommendations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class DietRecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public DietRecommendationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    private final RowMapper<DietRecommendations> rowMapper = (ResultSet rs, int rowNum) ->
            new DietRecommendations(
                    rs.getInt("diet_id"),
                    rs.getInt("user_id"),
                    rs.getInt("goal_id"),
                    rs.getString("diet_name"),
                    rs.getString("diet_description"),
                    rs.getInt("calories_per_day"),
                    rs.getBigDecimal("protein_percentage"),
                    rs.getBigDecimal("carbs_percentage"),
                    rs.getBigDecimal("fat_percentage"),
                    rs.getString("meal_type"),
                    rs.getString("hydration_recommendation"),
                    rs.getString("foods_to_include"),
                    rs.getString("foods_to_avoid"),
                    rs.getString("supplements_recommended"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            );

    public List<DietRecommendations> findAll() {
        String sql = "SELECT * FROM dietrecommendations";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public DietRecommendations findById(int dietId) {
        String sql = "SELECT * FROM dietrecommendations WHERE diet_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, dietId);
    }

    public int save(DietRecommendations diet) {
        String sql = "INSERT INTO dietrecommendations " +
                "(user_id, goal_id, diet_name, diet_description, calories_per_day, protein_percentage, carbs_percentage, " +
                "fat_percentage, meal_type, hydration_recommendation, foods_to_include, foods_to_avoid, supplements_recommended) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                diet.getUserId(),
                diet.getGoalId(),
                diet.getDietName(),
                diet.getDietDescription(),
                diet.getCaloriesPerDay(),
                diet.getProteinPercentage(),
                diet.getCarbsPercentage(),
                diet.getFatPercentage(),
                diet.getMealType(),
                diet.getHydrationRecommendation(),
                diet.getFoodsToInclude(),
                diet.getFoodsToAvoid(),
                diet.getSupplementsRecommended()
        );
    }

    public int update(DietRecommendations diet) {
        StringBuilder sql = new StringBuilder("UPDATE dietrecommendations SET ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (diet.getDietName() != null) {
            sql.append("diet_name = :dietName, ");
            params.addValue("dietName", diet.getDietName());
        }
        if (diet.getDietDescription() != null) {
            sql.append("diet_description = :dietDescription, ");
            params.addValue("dietDescription", diet.getDietDescription());
        }
        if (diet.getCaloriesPerDay() != 0) {
            sql.append("calories_per_day = :caloriesPerDay, ");
            params.addValue("caloriesPerDay", diet.getCaloriesPerDay());
        }
        if (diet.getProteinPercentage() != null) {
            sql.append("protein_percentage = :proteinPercentage, ");
            params.addValue("proteinPercentage", diet.getProteinPercentage());
        }
        if (diet.getCarbsPercentage() != null) {
            sql.append("carbs_percentage = :carbsPercentage, ");
            params.addValue("carbsPercentage", diet.getCarbsPercentage());
        }
        if (diet.getFatPercentage() != null) {
            sql.append("fat_percentage = :fatPercentage, ");
            params.addValue("fatPercentage", diet.getFatPercentage());
        }
        if (diet.getMealType() != null) {
            sql.append("meal_type = :mealType, ");
            params.addValue("mealFrequency", diet.getMealType());
        }
        if (diet.getHydrationRecommendation() != null) {
            sql.append("hydration_recommendation = :hydrationRecommendation, ");
            params.addValue("hydrationRecommendation", diet.getHydrationRecommendation());
        }
        if (diet.getFoodsToInclude() != null) {
            sql.append("foods_to_include = :foodsToInclude, ");
            params.addValue("foodsToInclude", diet.getFoodsToInclude());
        }
        if (diet.getFoodsToAvoid() != null) {
            sql.append("foods_to_avoid = :foodsToAvoid, ");
            params.addValue("foodsToAvoid", diet.getFoodsToAvoid());
        }
        if (diet.getSupplementsRecommended() != null) {
            sql.append("supplements_recommended = :supplementsRecommended, ");
            params.addValue("supplementsRecommended", diet.getSupplementsRecommended());
        }

        // Ensure at least one field is updated
        if (params.getValues().isEmpty()) {
            return 0; // No update needed
        }

        sql.append("updated_at = CURRENT_TIMESTAMP WHERE diet_id = :dietId");
        params.addValue("dietId", diet.getDietId());

        return namedJdbcTemplate.update(sql.toString(), params);
    }

    public int deleteById(int dietId) {
        String sql = "DELETE FROM dietrecommendations WHERE diet_id = ?";
        return jdbcTemplate.update(sql, dietId);
    }
}
