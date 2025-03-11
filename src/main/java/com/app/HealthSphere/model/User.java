package com.app.HealthSphere.model;

import java.util.Date;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Double height;
    private Double weight;
    private Double bmi; // Added BMI field
    private String phoneNumber;
    private String address;
    private String profilePictureUrl;
    private String bloodType;
    private String medicalConditions;
    private String allergies;
    private String medications;
    private String dietaryPreference;

    public User() {}

    public User(Long userId, String firstName, String lastName, Date dateOfBirth, String gender, Double height, Double weight,
                Double bmi, String phoneNumber, String address, String profilePictureUrl, String bloodType, String medicalConditions,
                String allergies, String medications, String dietaryPreference) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

        System.out.println("Before Assignment - Height: " + height + ", Weight: " + weight);

        this.height = height;
        this.weight = weight;

        System.out.println("After Assignment - Height: " + this.height + ", Weight: " + this.weight);
//        this.bmi = (bmi != null) ? bmi : calculateBMI(height, weight);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profilePictureUrl = profilePictureUrl;
        this.bloodType = bloodType;
        this.medicalConditions = medicalConditions;
        this.allergies = allergies;
        this.medications = medications;
        this.dietaryPreference = dietaryPreference;

        updateBMI();
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) {
        this.height = height;
        updateBMI(); // Update BMI when height changes
    }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) {
        this.weight = weight;
        updateBMI(); // Update BMI when weight changes
    }

    public Double getBmi() { return bmi; }

    private void updateBMI() {
        this.bmi = calculateBMI(this.height, this.weight);
    }

    private Double calculateBMI(Double height, Double weight) {
        if (height == null || weight == null || height <= 0) {
            System.out.println("BMI Calculation Skipped: Invalid height (" + height + ") or weight (" + weight + ")");
            return null;
        }
        double heightInMeters = height > 10 ? height / 100.0 : height;

        double bmi = weight / (heightInMeters * heightInMeters);
        System.out.println("BMI Calculated: " + bmi); // Debug log

        return bmi;

    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getMedicalConditions() { return medicalConditions; }
    public void setMedicalConditions(String medicalConditions) { this.medicalConditions = medicalConditions; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }

    public String getDietaryPreference() { return dietaryPreference; }
    public void setDietaryPreference(String dietaryPreference) { this.dietaryPreference = dietaryPreference; }


}
