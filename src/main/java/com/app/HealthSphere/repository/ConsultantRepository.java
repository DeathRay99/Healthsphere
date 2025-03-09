package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.Consultant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConsultantRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConsultantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for Consultant
    private final RowMapper<Consultant> consultantRowMapper = (rs, rowNum) -> new Consultant(
            rs.getInt("Consultants_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("designation"),
            rs.getString("phone_no"),
            rs.getString("email"),
            rs.getString("notes")
    );

    // Save a new Consultant
    public int save(Consultant consultant) {
        String sql = "INSERT INTO Consultants (first_name, last_name, designation, phone_no, email, notes) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, consultant.getFirstName(), consultant.getLastName(), consultant.getDesignation(),
                consultant.getPhoneNo(), consultant.getEmail(), consultant.getNotes());
    }

    // Retrieve all Consultants
    public List<Consultant> findAll() {
        String sql = "SELECT * FROM Consultants";
        return jdbcTemplate.query(sql, consultantRowMapper);
    }

    // Find a Consultant by ID
    public Consultant findById(int consultantId) {
        String sql = "SELECT * FROM Consultants WHERE Consultants_id = ?";
        return jdbcTemplate.queryForObject(sql, consultantRowMapper, consultantId);
    }

    // Update an existing Consultant
    public int update(Consultant consultant) {
        StringBuilder sql = new StringBuilder("UPDATE Consultants SET ");
        Map<String, Object> params = new HashMap<>();

        if (consultant.getFirstName() != null) {
            sql.append("first_name = ?, ");
            params.put("firstName", consultant.getFirstName());
        }
        if (consultant.getLastName() != null) {
            sql.append("last_name = ?, ");
            params.put("lastName", consultant.getLastName());
        }
        if (consultant.getDesignation() != null) {
            sql.append("designation = ?, ");
            params.put("designation", consultant.getDesignation());
        }
        if (consultant.getPhoneNo() != null) {
            sql.append("phone_no = ?, ");
            params.put("phoneNo", consultant.getPhoneNo());
        }
        if (consultant.getEmail() != null) {
            sql.append("email = ?, ");
            params.put("email", consultant.getEmail());
        }
        if (consultant.getNotes() != null) {
            sql.append("notes = ?, ");
            params.put("notes", consultant.getNotes());
        }

        // Remove the last comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE Consultants_id = ?");
        params.put("consultantId", consultant.getConsultantId());

        return jdbcTemplate.update(sql.toString(), params);
    }

    // Delete a Consultant by ID
    public int delete(int consultantId) {
        String sql = "DELETE FROM Consultants WHERE Consultants_id = ?";
        return jdbcTemplate.update(sql, consultantId);
    }
}
