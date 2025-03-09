package com.app.HealthSphere.model;

public class Consultant {
    private int consultantsId;
    private String firstName;
    private String lastName;
    private String designation;
    private String phoneNo;
    private String email;
    private String notes;

    // Default constructor
    public Consultant() {
    }

    // Parameterized constructor
    public Consultant(int consultantsId, String firstName, String lastName, String designation, String phoneNo,
            String email, String notes) {
        this.consultantsId = consultantsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.phoneNo = phoneNo;
        this.email = email;
        this.notes = notes;
    }

    // Getters and Setters


    public int getConsultantsId() {
        return consultantsId;
    }

    public void setConsultantsId(int consultantsId) {
        this.consultantsId = consultantsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
