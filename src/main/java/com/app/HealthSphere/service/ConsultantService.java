package com.app.HealthSphere.service;

import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.repository.ConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultantService {

    private final ConsultantRepository consultantRepository;

    public ConsultantService(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    // ✅ Save a new Consultant
    public int saveConsultant(Consultant consultant) {
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant cannot be null.");
        }
        try {
            return consultantRepository.save(consultant);
        } catch (Exception e) {
            throw new RuntimeException("Error saving consultant: " + e.getMessage());
        }
    }

    // ✅ Retrieve all Consultants
    public List<Consultant> findAllConsultants() {
        try {
            List<Consultant> consultants = consultantRepository.findAll();
            if (consultants.isEmpty()) {
                throw new IllegalStateException("No consultants found.");
            }
            return consultants;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving consultants: " + e.getMessage());
        }
    }

    // ✅ Find a Consultant by ID
    public Consultant findConsultantById(int consultantId) {
        try {
            Optional<Consultant> consultant = Optional.ofNullable(consultantRepository.findById(consultantId));
            if (consultant.isEmpty()) {
                throw new IllegalArgumentException("Consultant with ID " + consultantId + " not found.");
            }
            return consultant.get();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving consultant by ID: " + e.getMessage());
        }
    }

    // ✅ Update an existing Consultant
    public boolean updateConsultant(Consultant consultant) {
        if (consultant == null || consultant.getConsultantId() == 0) {
            throw new IllegalArgumentException("Consultant data or ID cannot be null or zero.");
        }

        try {
            int rowsUpdated = consultantRepository.update(consultant);
            if (rowsUpdated == 0) {
                throw new IllegalStateException("Failed to update consultant. Consultant with ID " + consultant.getConsultantId() + " does not exist.");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error updating consultant: " + e.getMessage());
        }
    }

    // ✅ Delete a Consultant by ID
    public boolean deleteConsultant(int consultantId) {
        if (consultantId <= 0) {
            throw new IllegalArgumentException("Consultant ID must be greater than zero.");
        }

        try {
            int rowsDeleted = consultantRepository.delete(consultantId);
            if (rowsDeleted == 0) {
                throw new IllegalStateException("Failed to delete consultant. Consultant with ID " + consultantId + " does not exist.");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting consultant: " + e.getMessage());
        }
    }
}
