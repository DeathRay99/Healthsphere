package com.app.HealthSphere.models;

import java.time.LocalDateTime;

public class Appointments {
    private Long appointmentId;
    private Long userId;
    private String providerName;
    private String appointmentType;
    private LocalDateTime appointmentDate;
    private String notes;

    public Appointments() {}

    public Appointments(Long userId, String providerName, String appointmentType, LocalDateTime appointmentDate, String notes) {
        this.userId = userId;
        this.providerName = providerName;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
    }

    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public String getAppointmentType() { return appointmentType; }
    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
