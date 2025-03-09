package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.Consultant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsultantRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConsultantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Consultant> consultantRowMapper = new RowMapper<Consultant>() {
        @Override
        public Consultant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Consultant consultant = new Consultant();
            consultant.setConsultantsId(rs.getInt("consultants_id"));
            consultant.setFirstName(rs.getString("first_name"));
            consultant.setLastName(rs.getString("last_name"));
            consultant.setDesignation(rs.getString("designation"));
            consultant.setPhoneNo(rs.getString("phone_no"));
            consultant.setEmail(rs.getString("email"));
            consultant.setNotes(rs.getString("notes"));
            return consultant;
        }
    };

    // Create a new consultant
    public int save(Consultant consultant) {
        String sql = "INSERT INTO Consultant (first_name, last_name, designation, phone_no, email, notes) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                consultant.getFirstName(),
                consultant.getLastName(),
                consultant.getDesignation(),
                consultant.getPhoneNo(),
                consultant.getEmail(),
                consultant.getNotes());
    }

    // Retrieve all Consultant
    public List<Consultant> findAll() {
        String sql = "SELECT * FROM Consultant";
        return jdbcTemplate.query(sql, consultantRowMapper);
    }

    // Find an consultant by ID
    public Consultant findById(int appointmentId) {
        String sql = "SELECT * FROM Consultant WHERE consultants_id = ?";
        return jdbcTemplate.queryForObject(sql, consultantRowMapper, appointmentId);
    }

    // Update an existing consultant
    public int update(Consultant consultant) {
        String sql = "UPDATE Consultants SET first_name = ?, last_name = ?, designation = ?, phone_no = ?, email = ?, notes = ? WHERE consultants_id = ?";
        return jdbcTemplate.update(sql,
                consultant.getFirstName(),
                consultant.getLastName(),
                consultant.getDesignation(),
                consultant.getPhoneNo(),
                consultant.getEmail(),
                consultant.getNotes(),
                consultant.getConsultantsId());
    }

    // Delete an consultant by ID
    public int deleteById(int consultantId) {
        String sql = "DELETE FROM Consultant WHERE consultants_id = ?";
        return jdbcTemplate.update(sql, consultantId);
    }
}
