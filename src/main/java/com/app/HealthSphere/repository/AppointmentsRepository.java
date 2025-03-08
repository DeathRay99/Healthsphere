package com.app.HealthSphere.repository;

import com.app.HealthSphere.model.Appointments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AppointmentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppointmentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Appointments> appointmentsRowMapper = new RowMapper<Appointments>() {
        @Override
        public Appointments mapRow(ResultSet rs, int rowNum) throws SQLException {
            Appointments appointment = new Appointments();
            appointment.setAppointmentId(rs.getInt("appointment_id"));
            appointment.setFirstName(rs.getString("first_name"));
            appointment.setLastName(rs.getString("last_name"));
            appointment.setDesignation(rs.getString("designation"));
            appointment.setPhoneNo(rs.getString("phone_no"));
            appointment.setEmail(rs.getString("email"));
            appointment.setNotes(rs.getString("notes"));
            return appointment;
        }
    };

    // Create a new appointment
    public int save(Appointments appointment) {
        String sql = "INSERT INTO Appointments (first_name, last_name, designation, phone_no, email, notes) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                appointment.getFirstName(),
                appointment.getLastName(),
                appointment.getDesignation(),
                appointment.getPhoneNo(),
                appointment.getEmail(),
                appointment.getNotes());
    }

    // Retrieve all appointments
    public List<Appointments> findAll() {
        String sql = "SELECT * FROM Appointments";
        return jdbcTemplate.query(sql, appointmentsRowMapper);
    }

    // Find an appointment by ID
    public Appointments findById(int appointmentId) {
        String sql = "SELECT * FROM Appointments WHERE appointment_id = ?";
        return jdbcTemplate.queryForObject(sql, appointmentsRowMapper, appointmentId);
    }

    // Update an existing appointment
    public int update(Appointments appointment) {
        String sql = "UPDATE Appointments SET first_name = ?, last_name = ?, designation = ?, phone_no = ?, email = ?, notes = ? WHERE appointment_id = ?";
        return jdbcTemplate.update(sql,
                appointment.getFirstName(),
                appointment.getLastName(),
                appointment.getDesignation(),
                appointment.getPhoneNo(),
                appointment.getEmail(),
                appointment.getNotes(),
                appointment.getAppointmentId());
    }

    // Delete an appointment by ID
    public int deleteById(int appointmentId) {
        String sql = "DELETE FROM Appointments WHERE appointment_id = ?";
        return jdbcTemplate.update(sql, appointmentId);
    }
}
