package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.HealthLog;
import com.app.HealthSphere.service.HealthLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthLogs")
public class HealthLogController {

    private final HealthLogService healthLogService;

    @Autowired
    public HealthLogController(HealthLogService healthLogService) {
        this.healthLogService = healthLogService;
    }

    // Create a new health log
    @PostMapping
    public ResponseEntity<String> createHealthLog(@RequestBody HealthLog healthLog) {
        healthLogService.saveHealthLog(healthLog);
        return new ResponseEntity<>("Health log created successfully", HttpStatus.CREATED);
    }

    // Retrieve all health logs
    @GetMapping
    public ResponseEntity<List<HealthLog>> getAllHealthLogs() {
        List<HealthLog> healthLogs = healthLogService.findAllHealthLogs();
        return new ResponseEntity<>(healthLogs, HttpStatus.OK);
    }

    // Retrieve a health log by ID
    @GetMapping("/{id}")
    public ResponseEntity<HealthLog> getHealthLogById(@PathVariable Long id) {
        HealthLog healthLog = healthLogService.findHealthLogById(id);
        return new ResponseEntity<>(healthLog, HttpStatus.OK);
    }

    // Update a health log
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHealthLog(@PathVariable Long id, @RequestBody HealthLog healthLog) {
        healthLog.setLogId(id);
        healthLogService.updateHealthLog(healthLog);
        return new ResponseEntity<>("Health log updated successfully", HttpStatus.OK);
    }

    // Delete a health log by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHealthLog(@PathVariable Long id) {
        healthLogService.deleteHealthLog(id);
        return new ResponseEntity<>("Health log deleted successfully", HttpStatus.OK);
    }
}
