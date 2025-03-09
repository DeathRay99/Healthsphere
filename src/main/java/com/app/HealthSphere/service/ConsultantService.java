package com.app.HealthSphere.service;

import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.repository.ConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantService {

    private final ConsultantRepository appointmentsRepository;

    public ConsultantService(ConsultantRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    // Save consultant
    public int saveConsultant(Consultant consultant) {
        return appointmentsRepository.save(consultant);
    }

    // Find all Consultant
    public List<Consultant> findAllConsultants() {
        return appointmentsRepository.findAll();
    }

    // Find consultant by ID
    public Consultant findConsultantById(int appointmentId) {
        return appointmentsRepository.findById(appointmentId);
    }

    // Update consultant
    public int updateConsultant(Consultant consultant) {
        return appointmentsRepository.update(consultant);
    }

    // Delete consultant by ID
    public int deleteConsultantById(int appointmentId) {
        return appointmentsRepository.deleteById(appointmentId);
    }
}
