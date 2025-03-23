//package com.app.HealthSphere.service;
//
//import com.app.HealthSphere.model.HealthLog;
//import com.app.HealthSphere.repository.HealthLogRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class HealthLogService {
//
//    private final HealthLogRepository healthLogRepository;
//
//    public HealthLogService(HealthLogRepository healthLogRepository) {
//        this.healthLogRepository = healthLogRepository;
//    }
//
//    // Save health log
//    public int saveHealthLog(HealthLog healthLog) {
//        return healthLogRepository.save(healthLog);
//    }
//
//    // Find all health logs
//    public List<HealthLog> findAllHealthLogs() {
//        return healthLogRepository.findAll();
//    }
//
//    // Find health log by ID
//    public HealthLog findHealthLogById(Long logId) {
//        return healthLogRepository.findById(logId);
//    }
//
//    // Update health log
//    public int updateHealthLog(HealthLog healthLog) {
//        return healthLogRepository.update(healthLog);
//    }
//
//    // Delete health log by ID
//    public int deleteHealthLog(Long logId) {
//        return healthLogRepository.delete(logId);
//    }
//}
package com.app.HealthSphere.service;

import com.app.HealthSphere.model.HealthLog;
import com.app.HealthSphere.repository.HealthLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthLogService {

    private final HealthLogRepository healthLogRepository;

    public HealthLogService(HealthLogRepository healthLogRepository) {
        this.healthLogRepository = healthLogRepository;
    }

    // Save health log
    public int saveHealthLog(HealthLog healthLog) {
        return healthLogRepository.save(healthLog);
    }

    // Find all health logs
    public List<HealthLog> findAllHealthLogs() {
        return healthLogRepository.findAll();
    }

    // Find health log by ID
    public HealthLog findHealthLogById(Long logId) {
        return healthLogRepository.findById(logId);
    }

    // Find health logs by user ID (NEW METHOD)
    public List<HealthLog> findHealthLogsByUserId(Long userId) {
        return healthLogRepository.findByUserId(userId);
    }

    // Update health log
    public int updateHealthLog(HealthLog healthLog) {
        return healthLogRepository.update(healthLog);
    }

    // Delete health log by ID
    public int deleteHealthLog(Long logId) {
        return healthLogRepository.delete(logId);
    }
}
