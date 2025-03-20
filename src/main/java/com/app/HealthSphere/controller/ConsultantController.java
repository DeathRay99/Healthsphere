//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.Consultant;
//import com.app.HealthSphere.service.ConsultantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/consultants")
//public class ConsultantController {
//
//    private final ConsultantService consultantService;
//
//    @Autowired
//    public ConsultantController(ConsultantService consultantService) {
//        this.consultantService = consultantService;
//    }
//
//    // Create a new Consultant
//    @PostMapping
//    public int createConsultant(@RequestBody Consultant consultant) {
//        return consultantService.saveConsultant(consultant);
//    }
//
//    // Retrieve a Consultant by ID
//    @GetMapping("/{id}")
//    public Consultant getConsultant(@PathVariable int id) {
//        return consultantService.getConsultantById(id);
//    }
//
//    // Retrieve all Consultants
//    @GetMapping
//    public List<Consultant> getAllConsultants() {
//        return consultantService.getAllConsultants();
//    }
//
//    // Update an existing Consultant
//    @PutMapping
//    public int updateConsultant(@RequestBody Consultant consultant) {
//        return consultantService.updateConsultant(consultant);
//    }
//
//    // Delete a Consultant by ID
//    @DeleteMapping("/{id}")
//    public int deleteConsultant(@PathVariable int id) {
//        return consultantService.deleteConsultant(id);
//    }
//}
package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/consultants")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ConsultantController {

    private final ConsultantService consultantService;

    @Autowired
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // ✅ Create a new Consultant
    @PostMapping
    public ResponseEntity<String> createConsultant(@RequestBody Consultant consultant) {
        int rowsAffected = consultantService.saveConsultant(consultant);
        return ResponseEntity.status(HttpStatus.CREATED).body(rowsAffected + " Consultant created successfully.");
    }

    // ✅ Retrieve all Consultants
    @GetMapping
    public ResponseEntity<List<Consultant>> getAllConsultants() {
        List<Consultant> consultants = consultantService.findAllConsultants();
        return ResponseEntity.ok(consultants);
    }

    // ✅ Retrieve a Consultant by ID
    @GetMapping("/{consultantId}")
    public ResponseEntity<Consultant> getConsultantById(@PathVariable int consultantId) {
        Consultant consultant = consultantService.findConsultantById(consultantId);
        return ResponseEntity.ok(consultant);
    }

    // ✅ Update an existing Consultant
    @PutMapping("/{consultantId}")
    public ResponseEntity<String> updateConsultant(@PathVariable int consultantId, @RequestBody Consultant consultant) {
        consultant.setConsultantId(consultantId);
        int rowsAffected = consultantService.updateConsultant(consultant);
        return ResponseEntity.ok(rowsAffected + " Consultant updated successfully.");
    }

    // ✅ Delete a Consultant by ID
    @DeleteMapping("/{consultantId}")
    public ResponseEntity<String> deleteConsultant(@PathVariable int consultantId) {
        int rowsAffected = consultantService.deleteConsultant(consultantId);
        return ResponseEntity.ok(rowsAffected + " Consultant deleted successfully.");
    }
}
