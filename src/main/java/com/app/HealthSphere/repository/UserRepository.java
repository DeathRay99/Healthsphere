package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for User
    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User(
                    rs.getLong("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth"),
                    rs.getString("gender"),
                    rs.getDouble("height"),
                    rs.getDouble("weight"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("profile_picture_url"),
                    rs.getString("blood_type"),
                    rs.getString("medical_conditions"),
                    rs.getString("allergies"),
                    rs.getString("medications"),
                    rs.getString("dietary_preference")
            );

    // Create
    public int save(User user) {
        String sql = "INSERT INTO Users (first_name, last_name, date_of_birth, gender, height, weight, phone_number, address, profile_picture_url, blood_type, medical_conditions, allergies, medications, dietary_preference) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getHeight(),
                user.getWeight(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getProfilePictureUrl(),
                user.getBloodType(),
                user.getMedicalConditions(),
                user.getAllergies(),
                user.getMedications(),
                user.getDietaryPreference()
        );
    }

    // Read (Find all users)
    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    // Read (Find by ID)
    public User findById(Long userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
    }

    // Update
    public int update(User user) {
        String sql = "UPDATE Users SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, height = ?, weight = ?, phone_number = ?, address = ?, profile_picture_url = ?, blood_type = ?, medical_conditions = ?, allergies = ?, medications = ?, dietary_preference = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getHeight(),
                user.getWeight(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getProfilePictureUrl(),
                user.getBloodType(),
                user.getMedicalConditions(),
                user.getAllergies(),
                user.getMedications(),
                user.getDietaryPreference(),
                user.getUserId()
        );
    }

    // Delete
    public int delete(Long userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        return jdbcTemplate.update(sql, userId);
    }
}
