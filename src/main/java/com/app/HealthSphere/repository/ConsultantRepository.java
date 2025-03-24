package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.Consultant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultantRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConsultantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for Consultant
    private final RowMapper<Consultant> consultantRowMapper = (rs, rowNum) -> {
        Consultant consultant = new Consultant();
        consultant.setConsultantId(rs.getInt("Consultants_id"));
        consultant.setFirstName(rs.getString("first_name"));
        consultant.setLastName(rs.getString("last_name"));
        consultant.setDesignation(rs.getString("designation"));
        consultant.setPhoneNo(rs.getString("phone_no"));
        consultant.setEmail(rs.getString("email"));
        consultant.setNotes(rs.getString("notes"));
        return consultant;
    };

    // Save a new Consultant
    public int save(Consultant consultant) {
        try {
            String sql = "INSERT INTO Consultants (first_name, last_name, designation, phone_no, email, notes) VALUES (?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql,
                    consultant.getFirstName(),
                    consultant.getLastName(),
                    consultant.getDesignation(),
                    consultant.getPhoneNo(),
                    consultant.getEmail(),
                    consultant.getNotes()
            );
        } catch (Exception e) {
            System.err.println("Error saving consultant: " + e.getMessage());
            throw e; // Rethrow the exception to be handled by the service layer
        }
    }

    // Retrieve all Consultants
    public List<Consultant> findAll() {
        try {
            String sql = "SELECT * FROM Consultants";
            return jdbcTemplate.query(sql, consultantRowMapper);
        } catch (Exception e) {
            System.err.println("Error retrieving consultants: " + e.getMessage());
            throw e; // Rethrow the exception to be handled by the service layer
        }
    }

    // Find a Consultant by ID
    public Consultant findById(int consultantId) {
        String sql = "SELECT * FROM Consultants WHERE Consultants_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, consultantRowMapper, consultantId);
        } catch (Exception e) {
            System.err.println("Consultant not found with ID: " + consultantId + " - Error: " + e.getMessage());
            return null; // Return null if no consultant is found
        }
    }

    // Update an existing Consultant
    public int update(Consultant consultant) {
        try {
            String sql = "UPDATE Consultants SET first_name = ?, last_name = ?, designation = ?, phone_no = ?, email = ?, notes = ? WHERE Consultants_id = ?";
            return jdbcTemplate.update(sql,
                    consultant.getFirstName(),
                    consultant.getLastName(),
                    consultant.getDesignation(),
                    consultant.getPhoneNo(),
                    consultant.getEmail(),
                    consultant.getNotes(),
                    consultant.getConsultantId()
            );
        } catch (Exception e) {
            System.err.println("Error updating consultant: " + e.getMessage());
            throw e; // Rethrow the exception to be handled by the service layer
        }
    }

    // Delete a Consultant by ID
    public int delete(int consultantId) {
        try {
            String sql = "DELETE FROM Consultants WHERE Consultants_id = ?";
            return jdbcTemplate.update(sql, consultantId);
        } catch (Exception e) {
            System.err.println("Error deleting consultant: " + e.getMessage());
            throw e; // Rethrow the exception to be handled by the service layer
        }
    }
}
