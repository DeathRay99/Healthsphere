//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.HealthLog;
//import com.app.HealthSphere.service.HealthLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/healthLogs")
//public class HealthLogController {
//
//    private final HealthLogService healthLogService;
//
//    @Autowired
//    public HealthLogController(HealthLogService healthLogService) {
//        this.healthLogService = healthLogService;
//    }
//
//    // Create a new health log
//    @PostMapping
//    public ResponseEntity<String> createHealthLog(@RequestBody HealthLog healthLog) {
//        healthLogService.saveHealthLog(healthLog);
//        return new ResponseEntity<>("Health log created successfully", HttpStatus.CREATED);
//    }
//
//    // Retrieve all health logs
//    @GetMapping
//    public ResponseEntity<List<HealthLog>> getAllHealthLogs() {
//        List<HealthLog> healthLogs = healthLogService.findAllHealthLogs();
//        return new ResponseEntity<>(healthLogs, HttpStatus.OK);
//    }
//
//    // Retrieve a health log by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<HealthLog> getHealthLogById(@PathVariable Long id) {
//        HealthLog healthLog = healthLogService.findHealthLogById(id);
//        return new ResponseEntity<>(healthLog, HttpStatus.OK);
//    }
//
//    // Update a health log
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateHealthLog(@PathVariable Long id, @RequestBody HealthLog healthLog) {
//        healthLog.setLogId(id);
//        healthLogService.updateHealthLog(healthLog);
//        return new ResponseEntity<>("Health log updated successfully", HttpStatus.OK);
//    }
//
//    // Delete a health log by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteHealthLog(@PathVariable Long id) {
//        healthLogService.deleteHealthLog(id);
//        return new ResponseEntity<>("Health log deleted successfully", HttpStatus.OK);
//    }
//}

//package com.app.HealthSphere.controller;
//
//import com.app.HealthSphere.model.HealthLog;
//import com.app.HealthSphere.service.HealthLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/healthLogs")
//public class HealthLogController {
//
//    private final HealthLogService healthLogService;
//
//    @Autowired
//    public HealthLogController(HealthLogService healthLogService) {
//        this.healthLogService = healthLogService;
//    }
//
//    private boolean isAdmin(String role) {
//        return "ADMIN".equalsIgnoreCase(role);
//    }
//
//    // ✅ Create a new health log (User-only)
//    @PostMapping
//    public ResponseEntity<String> createHealthLog(@RequestBody HealthLog healthLog, @RequestHeader("Role") String role) {
//        if (isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
//        }
//        healthLogService.saveHealthLog(healthLog);
//        return new ResponseEntity<>("Health log created successfully", HttpStatus.CREATED);
//    }
//
//    // ✅ Retrieve all health logs (Admin-only)
//    @GetMapping
//    public ResponseEntity<List<HealthLog>> getAllHealthLogs(@RequestHeader("Role") String role) {
//        if (!isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }
//        List<HealthLog> healthLogs = healthLogService.findAllHealthLogs();
//        return new ResponseEntity<>(healthLogs, HttpStatus.OK);
//    }
//
//    // ✅ Retrieve a health log by ID (User can see only their own log)
//    @GetMapping("/{id}")
//    public ResponseEntity<HealthLog> getHealthLogById(@PathVariable Long id, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
//        HealthLog healthLog = healthLogService.findHealthLogById(id);
//        if (isAdmin(role) || healthLog.getUserId().equals(userId)) {
//            return new ResponseEntity<>(healthLog, HttpStatus.OK);
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//    }
//
//    // ✅ Update a health log (User-only)
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateHealthLog(@PathVariable Long id, @RequestBody HealthLog healthLog, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
//        if (isAdmin(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Users only.");
//        }
//        if (!healthLog.getUserId().equals(userId)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. You can only update your own health logs.");
//        }
//        healthLog.setLogId(id);
//        healthLogService.updateHealthLog(healthLog);
//        return new ResponseEntity<>("Health log updated successfully", HttpStatus.OK);
//    }
//
//    // ✅ Delete a health log by ID (Accessible to both Admin and User)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteHealthLog(@PathVariable Long id) {
//        healthLogService.deleteHealthLog(id);
//        return new ResponseEntity<>("Health log deleted successfully", HttpStatus.OK);
//    }
//}


package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.HealthLog;
import com.app.HealthSphere.service.HealthLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/healthLogs")
public class HealthLogController {

    private final HealthLogService healthLogService;

    @Autowired
    public HealthLogController(HealthLogService healthLogService) {
        this.healthLogService = healthLogService;
    }

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // ✅ Create a new health log (User-only)
    @PostMapping
    public ResponseEntity<Map<String, Object>> createHealthLog(@RequestBody HealthLog healthLog, @RequestHeader("Role") String role) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Users only."));
        }
        healthLogService.saveHealthLog(healthLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", "Health log created successfully."));
    }

    // ✅ Retrieve all health logs (Admin-only)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllHealthLogs(@RequestHeader("Role") String role) {
        if (!isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Admins only."));
        }
        List<HealthLog> healthLogs = healthLogService.findAllHealthLogs();
        return new ResponseEntity<>(Map.of("healthLogs", healthLogs), HttpStatus.OK);
    }

    // ✅ Retrieve a health log by ID (User can see only their own log)
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getHealthLogById(@PathVariable Long id, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
        HealthLog healthLog = healthLogService.findHealthLogById(id);
        if (isAdmin(role) || healthLog.getUserId().equals(userId)) {
            return new ResponseEntity<>(Map.of("healthLog", healthLog), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. You can only view your own health logs."));
    }

    // ✅ Update a health log (User-only)
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateHealthLog(@PathVariable Long id, @RequestBody HealthLog healthLog, @RequestHeader("Role") String role, @RequestHeader("UserId") Long userId) {
        if (isAdmin(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. Users only."));
        }
        if (!healthLog.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("err", "Access denied. You can only update your own health logs."));
        }
        healthLog.setLogId(id);
        healthLogService.updateHealthLog(healthLog);
        return new ResponseEntity<>(Map.of("response", "Health log updated successfully."), HttpStatus.OK);
    }

    // ✅ Delete a health log by ID (Accessible to both Admin and User)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteHealthLog(@PathVariable Long id) {
        healthLogService.deleteHealthLog(id);
        return new ResponseEntity<>(Map.of("response", "Health log deleted successfully."), HttpStatus.OK);
    }
}

