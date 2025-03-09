package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {
    private final ConsultantService consultantService;

    @Autowired
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // Create a new Consultant
    @PostMapping
    public ResponseEntity<String> addConsultant(@RequestBody Consultant consultant) {
        int result = consultantService.saveConsultant(consultant);
        if (result > 0) {
            return new ResponseEntity<>("Consultant added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding consultant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Retrieve a Consultant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Consultant> getConsultantById(@PathVariable int id) {
        Consultant consultant = consultantService.getConsultantById(id);
        if (consultant != null) {
            return new ResponseEntity<>(consultant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all Consultants
    @GetMapping
    public ResponseEntity<List<Consultant>> getAllConsultants() {
        List<Consultant> consultants = consultantService.getAllConsultants();
        if (consultants != null && !consultants.isEmpty()) {
            return new ResponseEntity<>(consultants, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Update an existing Consultant
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateConsultant(@PathVariable int id, @RequestBody Consultant consultant) {
        consultant.setConsultantId(id);
        int result = consultantService.updateConsultant(consultant);
        if (result > 0) {
            return new ResponseEntity<>("Consultant updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error updating consultant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a Consultant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultant(@PathVariable int id) {
        int result = consultantService.deleteConsultant(id);
        if (result > 0) {
            return new ResponseEntity<>("Consultant deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error deleting consultant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
