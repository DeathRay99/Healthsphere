package com.app.HealthSphere.model;

public class Consultant {
    private int consultantId;
    private String firstName;
    private String lastName;
    private String designation;
    private String phoneNo;
    private String email;
    private String notes;

    // Default constructor
    public Consultant() {}

    // Parameterized constructor with validation
    public Consultant(int consultantId, String firstName, String lastName, String designation, String phoneNo, String email, String notes) {
        setConsultantId(consultantId);
        setFirstName(firstName);
        setLastName(lastName);
        setDesignation(designation);
        setPhoneNo(phoneNo);
        setEmail(email);
        this.notes = notes; // Notes is optional, no validation required
    }

    // Getters and Setters with Validation
    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        if (consultantId <= 0) {
            throw new IllegalArgumentException("Consultant ID must be a positive integer.");
        }
        this.consultantId = consultantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("Designation cannot be null or empty.");
        }
        this.designation = designation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo == null || !phoneNo.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be a 10-digit numeric value.");
        }
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        // Notes is optional, so no validation is required
        this.notes = notes;
    }

    // Debugging Utility: toString() Override
    @Override
    public String toString() {
        return "Consultant{" +
                "consultantId=" + consultantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation='" + designation + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    // Validation of all fields (optional, for bulk updates or reconstruction)
    private void validateFields() {
        setConsultantId(consultantId);
        setFirstName(firstName);
        setLastName(lastName);
        setDesignation(designation);
        setPhoneNo(phoneNo);
        setEmail(email);
    }
}
