package com.app.HealthSphere.controller;

import java.util.*;
import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/consultants")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ConsultantController {

    private final ConsultantService consultantService;

    @Autowired
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // Helper method to check if the user is an admin
    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // ✅ Create a new Consultant (Admin-only)
    @PostMapping
    public ResponseEntity<Map<String, Object>> createConsultant(@RequestBody Consultant consultant, @RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied, admins only."));
        }
        try {
            consultantService.saveConsultant(consultant);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", "Consultant added successfully"));
        } catch (Exception e) {
            System.err.println("Error adding consultant: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("err", "Failed to add consultant."));
        }
    }

    // ✅ Retrieve all Consultants (Accessible to all)
    @GetMapping
    public ResponseEntity<Object> getAllConsultants() {
        try {
            List<Consultant> consultants = consultantService.findAllConsultants();
            if (consultants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "No consultants found."));
            }
            return ResponseEntity.ok(consultants);
        } catch (Exception e) {
            System.err.println("Error retrieving consultants: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("err", "An unexpected error occurred while retrieving consultants."));
        }
    }

    // ✅ Retrieve a Consultant by ID (Accessible to all)
    @GetMapping("/{consultantId}")
    public ResponseEntity<Object> getConsultantById(@PathVariable int consultantId) {
        try {
            Consultant consultant = consultantService.findConsultantById(consultantId);
            if (consultant == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("err", "Consultant not found."));
            }
            return ResponseEntity.ok(consultant);
        } catch (Exception e) {
            System.err.println("Error retrieving consultant by ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("err", "An unexpected error occurred while retrieving the consultant."));
        }
    }

    // ✅ Update an existing Consultant (Admin-only)
    @PutMapping("/{consultantId}")
    public ResponseEntity<Map<String, Object>> updateConsultant(@PathVariable int consultantId, @RequestBody Consultant consultant, @RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied, admins only."));
        }

        try {
            consultant.setConsultantId(consultantId); // Ensure the ID is properly set in the object
            boolean isUpdated = consultantService.updateConsultant(consultant);

            if (!isUpdated) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("err", "Consultant not found."));
            }

            return ResponseEntity.ok(Map.of("response", "Consultant updated successfully"));
        } catch (Exception e) {
            System.err.println("Error updating consultant: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("err", "An unexpected error occurred while updating the consultant."));
        }
    }

    // ✅ Delete a Consultant by ID (Admin-only)
    @DeleteMapping("/{consultantId}")
    public ResponseEntity<Map<String, Object>> deleteConsultant(@PathVariable int consultantId, @RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied, admins only."));
        }

        try {
            boolean isDeleted = consultantService.deleteConsultant(consultantId);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("err", "Consultant not found."));
            }

            return ResponseEntity.ok(Map.of("response", "Consultant deleted successfully"));
        } catch (Exception e) {
            System.err.println("Error deleting consultant: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("err", "An unexpected error occurred while deleting the consultant."));
        }
    }
}
