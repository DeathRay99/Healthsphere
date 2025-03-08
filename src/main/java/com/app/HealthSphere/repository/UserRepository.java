package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for User
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
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
            rs.getString("dietary_preference"));

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
                user.getDietaryPreference());
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
        StringBuilder sql = new StringBuilder("UPDATE Users SET ");
        Map<String, Object> params = new HashMap<>();

        if (user.getFirstName() != null) {
            sql.append("first_name = :firstName, ");
            params.put("firstName", user.getFirstName());
        }
        if (user.getLastName() != null) {
            sql.append("last_name = :lastName, ");
            params.put("lastName", user.getLastName());
        }
        if (user.getDateOfBirth() != null) {
            sql.append("date_of_birth = :dateOfBirth, ");
            params.put("dateOfBirth", user.getDateOfBirth());
        }
        if (user.getGender() != null) {
            sql.append("gender = :gender, ");
            params.put("gender", user.getGender());
        }
        if (user.getHeight() != null) {
            sql.append("height = :height, ");
            params.put("height", user.getHeight());
        }
        if (user.getWeight() != null) {
            sql.append("weight = :weight, ");
            params.put("weight", user.getWeight());
        }
        if (user.getPhoneNumber() != null) {
            sql.append("phone_number = :phoneNumber, ");
            params.put("phoneNumber", user.getPhoneNumber());
        }
        if (user.getAddress() != null) {
            sql.append("address = :address, ");
            params.put("address", user.getAddress());
        }
        if (user.getProfilePictureUrl() != null) {
            sql.append("profile_picture_url = :profilePictureUrl, ");
            params.put("profilePictureUrl", user.getProfilePictureUrl());
        }
        if (user.getBloodType() != null) {
            sql.append("blood_type = :bloodType, ");
            params.put("bloodType", user.getBloodType());
        }
        if (user.getMedicalConditions() != null) {
            sql.append("medical_conditions = :medicalConditions, ");
            params.put("medicalConditions", user.getMedicalConditions());
        }
        if (user.getAllergies() != null) {
            sql.append("allergies = :allergies, ");
            params.put("allergies", user.getAllergies());
        }
        if (user.getMedications() != null) {
            sql.append("medications = :medications, ");
            params.put("medications", user.getMedications());
        }
        if (user.getDietaryPreference() != null) {
            sql.append("dietary_preference = :dietaryPreference, ");
            params.put("dietaryPreference", user.getDietaryPreference());
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE user_id = :userId");
        params.put("userId", user.getUserId());

        return jdbcTemplate.update(sql.toString(), params);
    }

    // Delete
    public int delete(Long userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        return jdbcTemplate.update(sql, userId);
    }
}
