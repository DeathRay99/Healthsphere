package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.DietRecommendations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DietRecommendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public DietRecommendationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DietRecommendations> dietRecommendationsRowMapper = new RowMapper<DietRecommendations>() {
        @Override
        public DietRecommendations mapRow(ResultSet rs, int rowNum) throws SQLException {
            DietRecommendations dietRecommendation = new DietRecommendations();
            dietRecommendation.setDietId(rs.getInt("diet_id"));
            dietRecommendation.setDietName(rs.getString("diet_name"));
            dietRecommendation.setDietDescription(rs.getString("diet_description"));
            dietRecommendation.setCaloriesPerDay(rs.getInt("calories_per_day"));
            dietRecommendation.setProteinPercentage(rs.getBigDecimal("protein_percentage"));
            dietRecommendation.setCarbsPercentage(rs.getBigDecimal("carbs_percentage"));
            dietRecommendation.setFatPercentage(rs.getBigDecimal("fat_percentage"));
            dietRecommendation.setMealFrequency(rs.getInt("meal_frequency"));
            dietRecommendation.setHydrationRecommendation(rs.getString("hydration_recommendation"));
            dietRecommendation.setFoodsToInclude(rs.getString("foods_to_include"));
            dietRecommendation.setFoodsToAvoid(rs.getString("foods_to_avoid"));
            dietRecommendation.setSupplementsRecommended(rs.getString("supplements_recommended"));
            dietRecommendation.setCreatedAt(rs.getTimestamp("created_at"));
            dietRecommendation.setUpdatedAt(rs.getTimestamp("updated_at"));
            return dietRecommendation;
        }
    };

    public List<DietRecommendations> findAll() {
        String sql = "SELECT * FROM dietRecommendations";
        return jdbcTemplate.query(sql, dietRecommendationsRowMapper);
    }

    public DietRecommendations findById(int dietId) {
        String sql = "SELECT * FROM dietRecommendations WHERE diet_id = ?";
        return jdbcTemplate.queryForObject(sql, dietRecommendationsRowMapper, dietId);
    }

    public int save(DietRecommendations dietRecommendations) {
        String sql = "INSERT INTO dietRecommendations (diet_name, diet_description, calories_per_day, protein_percentage, carbs_percentage, fat_percentage, meal_frequency, hydration_recommendation, foods_to_include, foods_to_avoid, supplements_recommended, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                dietRecommendations.getDietName(),
                dietRecommendations.getDietDescription(),
                dietRecommendations.getCaloriesPerDay(),
                dietRecommendations.getProteinPercentage(),
                dietRecommendations.getCarbsPercentage(),
                dietRecommendations.getFatPercentage(),
                dietRecommendations.getMealFrequency(),
                dietRecommendations.getHydrationRecommendation(),
                dietRecommendations.getFoodsToInclude(),
                dietRecommendations.getFoodsToAvoid(),
                dietRecommendations.getSupplementsRecommended(),
                dietRecommendations.getCreatedAt(),
                dietRecommendations.getUpdatedAt());
    }

    public int update(DietRecommendations dietRecommendations) {
        StringBuilder sql = new StringBuilder("UPDATE dietRecommendations SET ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (dietRecommendations.getDietName() != null) {
            sql.append("diet_name = :dietName, ");
            params.addValue("dietName", dietRecommendations.getDietName());
        }
        if (dietRecommendations.getDietDescription() != null) {
            sql.append("diet_description = :dietDescription, ");
            params.addValue("dietDescription", dietRecommendations.getDietDescription());
        }
        if (dietRecommendations.getCaloriesPerDay() != 0) {
            sql.append("calories_per_day = :caloriesPerDay, ");
            params.addValue("caloriesPerDay", dietRecommendations.getCaloriesPerDay());
        }
        if (dietRecommendations.getProteinPercentage() != null) {
            sql.append("protein_percentage = :proteinPercentage, ");
            params.addValue("proteinPercentage", dietRecommendations.getProteinPercentage());
        }
        if (dietRecommendations.getCarbsPercentage() != null) {
            sql.append("carbs_percentage = :carbsPercentage, ");
            params.addValue("carbsPercentage", dietRecommendations.getCarbsPercentage());
        }
        if (dietRecommendations.getFatPercentage() != null) {
            sql.append("fat_percentage = :fatPercentage, ");
            params.addValue("fatPercentage", dietRecommendations.getFatPercentage());
        }
        if (dietRecommendations.getMealFrequency() != 0) {
            sql.append("meal_frequency = :mealFrequency, ");
            params.addValue("mealFrequency", dietRecommendations.getMealFrequency());
        }
        if (dietRecommendations.getHydrationRecommendation() != null) {
            sql.append("hydration_recommendation = :hydrationRecommendation, ");
            params.addValue("hydrationRecommendation", dietRecommendations.getHydrationRecommendation());
        }
        if (dietRecommendations.getFoodsToInclude() != null) {
            sql.append("foods_to_include = :foodsToInclude, ");
            params.addValue("foodsToInclude", dietRecommendations.getFoodsToInclude());
        }
        if (dietRecommendations.getFoodsToAvoid() != null) {
            sql.append("foods_to_avoid = :foodsToAvoid, ");
            params.addValue("foodsToAvoid", dietRecommendations.getFoodsToAvoid());
        }
        if (dietRecommendations.getSupplementsRecommended() != null) {
            sql.append("supplements_recommended = :supplementsRecommended, ");
            params.addValue("supplementsRecommended", dietRecommendations.getSupplementsRecommended());
        }
        if (dietRecommendations.getCreatedAt() != null) {
            sql.append("created_at = :createdAt, ");
            params.addValue("createdAt", dietRecommendations.getCreatedAt());
        }
        if (dietRecommendations.getUpdatedAt() != null) {
            sql.append("updated_at = :updatedAt, ");
            params.addValue("updatedAt", dietRecommendations.getUpdatedAt());
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE diet_id = :dietId");
        params.addValue("dietId", dietRecommendations.getDietId());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    public int deleteById(int dietId) {
        String sql = "DELETE FROM dietRecommendations WHERE diet_id = ?";
        return jdbcTemplate.update(sql, dietId);
    }
}
