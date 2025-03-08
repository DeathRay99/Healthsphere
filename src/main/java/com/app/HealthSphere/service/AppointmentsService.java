package com.app.HealthSphere.service;

import com.app.HealthSphere.model.Appointments;
import com.app.HealthSphere.repository.AppointmentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;

    public AppointmentsService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    // Save appointment
    public int saveAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    // Find all appointments
    public List<Appointments> findAllAppointments() {
        return appointmentsRepository.findAll();
    }

    // Find appointment by ID
    public Appointments findAppointmentById(int appointmentId) {
        return appointmentsRepository.findById(appointmentId);
    }

    // Update appointment
    public int updateAppointment(Appointments appointment) {
        return appointmentsRepository.update(appointment);
    }

    // Delete appointment by ID
    public int deleteAppointmentById(int appointmentId) {
        return appointmentsRepository.deleteById(appointmentId);
    }
}
