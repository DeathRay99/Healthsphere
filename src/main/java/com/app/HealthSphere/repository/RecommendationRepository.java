package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.Recommendation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendationRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for Recommendation
    private final RowMapper<Recommendation> recommendationRowMapper = (rs, rowNum) ->
            new Recommendation(
                    rs.getLong("recommendation_id"),
                    rs.getLong("user_id"),
                    rs.getString("type"),
                    rs.getString("recommended_item"),
                    rs.getString("reason"),
                    rs.getString("user_feedback")
            );

    // Create
    public int save(Recommendation recommendation) {
        String sql = "INSERT INTO Recommendations (user_id, type, recommended_item, reason, user_feedback, recommended_date) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                recommendation.getUserId(),
                recommendation.getType(),
                recommendation.getRecommendedItem(),
                recommendation.getReason(),
                recommendation.getUserFeedback(),
                recommendation.getRecommendedDate()
        );
    }

    // Read (Find all recommendations)
    public List<Recommendation> findAll() {
        String sql = "SELECT * FROM Recommendations";
        return jdbcTemplate.query(sql, recommendationRowMapper);
    }

    // Read (Find by ID)
    public Recommendation findById(Long recommendationId) {
        String sql = "SELECT * FROM Recommendations WHERE recommendation_id = ?";
        return jdbcTemplate.queryForObject(sql, recommendationRowMapper, recommendationId);
    }

    // Update
    public int update(Recommendation recommendation) {
        String sql = "UPDATE Recommendations SET type = ?, recommended_item = ?, reason = ?, user_feedback = ? WHERE recommendation_id = ?";
        return jdbcTemplate.update(sql,
                recommendation.getType(),
                recommendation.getRecommendedItem(),
                recommendation.getReason(),
                recommendation.getUserFeedback(),
                recommendation.getRecommendationId()
        );
    }

    // Delete
    public int delete(Long recommendationId) {
        String sql = "DELETE FROM Recommendations WHERE recommendation_id = ?";
        return jdbcTemplate.update(sql, recommendationId);
    }

    // Update only the user_feedback field
    public int updateFeedback(Long recommendationId, String userFeedback) {
        String sql = "UPDATE Recommendations SET user_feedback = ? WHERE recommendation_id = ?";
        return jdbcTemplate.update(sql, userFeedback, recommendationId);
    }
}
