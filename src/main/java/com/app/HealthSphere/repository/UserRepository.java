package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
            rs.getDouble("bmi"),
            rs.getString("phone_number"),
            rs.getString("address"),
            rs.getString("profile_picture_url"),
            rs.getString("blood_type"),
            rs.getString("medical_conditions"),
            rs.getString("allergies"),
            rs.getString("medications"),
            rs.getString("dietary_preference"),
            rs.getInt("age")); // Added age field

    // Create a new user
    public int save(Long userId, User user) {
        String sql = "INSERT INTO Users (user_id, first_name, last_name, date_of_birth, gender, height, weight, bmi, phone_number, address, profile_picture_url, blood_type, medical_conditions, allergies, medications, dietary_preference, age) VALUES (:userId, :firstName, :lastName, :dateOfBirth, :gender, :height, :weight, :bmi, :phoneNumber, :address, :profilePictureUrl, :bloodType, :medicalConditions, :allergies, :medications, :dietaryPreference, :age)";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        params.put("dateOfBirth", user.getDateOfBirth());
        params.put("gender", user.getGender());
        params.put("height", user.getHeight());
        params.put("weight", user.getWeight());
        params.put("bmi", user.getBmi());
        params.put("phoneNumber", user.getPhoneNumber());
        params.put("address", user.getAddress());
        params.put("profilePictureUrl", user.getProfilePictureUrl());
        params.put("bloodType", user.getBloodType());
        params.put("medicalConditions", user.getMedicalConditions());
        params.put("allergies", user.getAllergies());
        params.put("medications", user.getMedications());
        params.put("dietaryPreference", user.getDietaryPreference());
        params.put("age", user.getAge()); // Added age parameter

        return namedParameterJdbcTemplate.update(sql, params);
    }

    // Get all users
    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return namedParameterJdbcTemplate.query(sql, userRowMapper);
    }

    // Get user by ID
    public User findById(Long userId) {
        String sql = "SELECT * FROM Users WHERE user_id = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, userRowMapper);
    }

    public int update(User user) {
        StringBuilder sql = new StringBuilder("UPDATE Users SET ");
        Map<String, Object> params = new HashMap<>();

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

        // If no fields were updated, return 0 (nothing changed)
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided for update.");
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE user_id = :userId");
        params.put("userId", user.getUserId());

        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    // Delete a user
    public int delete(Long userId) {
        String sql = "DELETE FROM Users WHERE user_id = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
