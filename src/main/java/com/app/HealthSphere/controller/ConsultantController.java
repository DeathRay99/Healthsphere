//
//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.Consultant;
//import com.app.HealthSphere.service.ConsultantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/consultants")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//public class ConsultantController {
//
//    private final ConsultantService consultantService;
//
//    @Autowired
//    public ConsultantController(ConsultantService consultantService) {
//        this.consultantService = consultantService;
//    }
//
//    // ✅ Create a new Consultant
//    @PostMapping
//    public ResponseEntity<String> createConsultant(@RequestBody Consultant consultant) {
//        int rowsAffected = consultantService.saveConsultant(consultant);
//        return ResponseEntity.status(HttpStatus.CREATED).body(rowsAffected + " Consultant created successfully.");
//    }
//
//    // ✅ Retrieve all Consultants
//    @GetMapping
//    public ResponseEntity<List<Consultant>> getAllConsultants() {
//        List<Consultant> consultants = consultantService.findAllConsultants();
//        return ResponseEntity.ok(consultants);
//    }
//
//    // ✅ Retrieve a Consultant by ID
//    @GetMapping("/{consultantId}")
//    public ResponseEntity<Consultant> getConsultantById(@PathVariable int consultantId) {
//        Consultant consultant = consultantService.findConsultantById(consultantId);
//        return ResponseEntity.ok(consultant);
//    }
//
//    // ✅ Update an existing Consultant
//    @PutMapping("/{consultantId}")
//    public ResponseEntity<String> updateConsultant(@PathVariable int consultantId, @RequestBody Consultant consultant) {
//        consultant.setConsultantId(consultantId);
//        int rowsAffected = consultantService.updateConsultant(consultant);
//        return ResponseEntity.ok(rowsAffected + " Consultant updated successfully.");
//    }
//
//    // ✅ Delete a Consultant by ID
//    @DeleteMapping("/{consultantId}")
//    public ResponseEntity<String> deleteConsultant(@PathVariable int consultantId) {
//        int rowsAffected = consultantService.deleteConsultant(consultantId);
//        return ResponseEntity.ok(rowsAffected + " Consultant deleted successfully.");
//    }
//}

package com.app.HealthSphere.controller;
import java.util.*;
import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        consultantService.saveConsultant(consultant);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of( "response", "Consultants added successfully"));
    }

    // ✅ Retrieve all Consultants (Accessible to all)
    @GetMapping
    public ResponseEntity<List<Consultant>> getAllConsultants() {
        // No restriction for GET requests
        List<Consultant> consultants = consultantService.findAllConsultants();
        return ResponseEntity.ok(consultants);
    }

    // ✅ Retrieve a Consultant by ID (Accessible to all)
    @GetMapping("/{consultantId}")
    public ResponseEntity<Consultant> getConsultantById(@PathVariable int consultantId, @RequestHeader("Role") String role) {
        // No restriction for GET requests
        Consultant consultant = consultantService.findConsultantById(consultantId);
        return ResponseEntity.ok(consultant);
    }

    // ✅ Update an existing Consultant (Admin-only)
    @PutMapping("/{consultantId}")
    public ResponseEntity<Map<String, Object>> updateConsultant(@PathVariable int consultantId, @RequestBody Consultant consultant, @RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied, admins only."));
        }
        consultant.setConsultantId(consultantId);
        consultantService.updateConsultant(consultant);
        return ResponseEntity.ok(Map.of( "response", "Consultants updated successfully"));
    }

    // ✅ Delete a Consultant by ID (Admin-only)
    @DeleteMapping("/{consultantId}")
    public ResponseEntity<Map<String,Object>> deleteConsultant(@PathVariable int consultantId, @RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied, admins only."));
        }
        consultantService.deleteConsultant(consultantId);
        return ResponseEntity.ok(Map.of( "response", "Consultants deleted successfully"));
    }
}
