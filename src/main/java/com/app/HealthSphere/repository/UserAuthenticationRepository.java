package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.UserAuthentication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAuthenticationRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserAuthenticationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper using Lambda
    private final RowMapper<UserAuthentication> userAuthenticationRowMapper = (rs, rowNum) ->
            new UserAuthentication(
                    rs.getLong("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("role")
            );

    // Save a new user
    public int save(UserAuthentication user) {
        String sql = "INSERT INTO UserAuthentication (username, email, password_hash, role) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getRole());
    }

    // Find user by username
    public Optional<UserAuthentication> findByUsername(String username) {
        String sql = "SELECT * FROM UserAuthentication WHERE username = ?";
        return jdbcTemplate.query(sql, userAuthenticationRowMapper, username).stream().findFirst();
    }

    // Update last login time
    public int updateLastLogin(String username) {
        String sql = "UPDATE UserAuthentication SET last_login = CURRENT_TIMESTAMP WHERE username = ?";
        return jdbcTemplate.update(sql, username);
    }
}
