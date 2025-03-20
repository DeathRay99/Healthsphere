//package com.app.HealthSphere.service;
//
//import com.app.HealthSphere.model.Consultant;
//import com.app.HealthSphere.repository.ConsultantRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ConsultantService {
//    private final ConsultantRepository consultantRepository;
//
//    @Autowired
//    public ConsultantService(ConsultantRepository consultantRepository) {
//        this.consultantRepository = consultantRepository;
//    }
//
//    // Create a new Consultant
//    public int saveConsultant(int consultantId, Consultant consultant) {
//        return consultantRepository.save(consultant);
//    }
//
//    // Retrieve a Consultant by ID
//    public Consultant getConsultantById(int consultantId) {
//        return consultantRepository.findById(consultantId);
//    }
//
//    // Retrieve all Consultants
//    public List<Consultant> getAllConsultants() {
//        return consultantRepository.findAll();
//    }
//
//    // Update an existing Consultant
//    public int updateConsultant(Consultant consultant) {
//        return consultantRepository.update(consultant);
//    }
//
//    // Delete a Consultant by ID
//    public int deleteConsultant(int consultantId) {
//        return consultantRepository.delete(consultantId);
//    }
//
//
//}
package com.app.HealthSphere.service;

import com.app.HealthSphere.model.Consultant;
import com.app.HealthSphere.repository.ConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantService {

    private final ConsultantRepository consultantRepository;

    public ConsultantService(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    // Save a new Consultant
    public int saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    // Retrieve all Consultants
    public List<Consultant> findAllConsultants() {
        return consultantRepository.findAll();
    }

    // Find a Consultant by ID
    public Consultant findConsultantById(int consultantId) {
        return consultantRepository.findById(consultantId);
    }

    // Update an existing Consultant
    public int updateConsultant(Consultant consultant) {
        // Directly update the consultant
        return consultantRepository.update(consultant);
    }

    // Delete a Consultant by ID
    public int deleteConsultant(int consultantId) {
        // Directly delete the consultant
        return consultantRepository.delete(consultantId);
    }
}

