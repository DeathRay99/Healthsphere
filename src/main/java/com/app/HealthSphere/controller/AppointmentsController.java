package com.app.HealthSphere.controller;

import com.app.HealthSphere.model.Appointments;
import com.app.HealthSphere.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    @Autowired
    public AppointmentsController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    // Create a new appointment
    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody Appointments appointment) {
        appointmentsService.saveAppointment(appointment);
        return new ResponseEntity<>("Appointment created successfully", HttpStatus.CREATED);
    }

    // Retrieve all appointments
    @GetMapping
    public ResponseEntity<List<Appointments>> getAllAppointments() {
        List<Appointments> appointments = appointmentsService.findAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Retrieve an appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable int id) {
        Appointments appointment = appointmentsService.findAppointmentById(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    // Update an appointment
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable int id, @RequestBody Appointments appointment) {
        appointment.setAppointmentId(id);
        appointmentsService.updateAppointment(appointment);
        return new ResponseEntity<>("Appointment updated successfully", HttpStatus.OK);
    }

    // Delete an appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        appointmentsService.deleteAppointmentById(id);
        return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
    }
}
