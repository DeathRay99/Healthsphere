package com.app.HealthSphere.model;

import java.util.Date;
import java.util.Calendar;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Double height;
    private Double weight;
    private Double bmi;
    private String phoneNumber;
    private String address;
    private String profilePictureUrl;
    private String bloodType;
    private String medicalConditions;
    private String allergies;
    private String medications;
    private String dietaryPreference;
    private Integer age;

    public User() {}

    public User(Long userId, String firstName, String lastName, Date dateOfBirth, String gender, Double height, Double weight,
                Double bmi, String phoneNumber, String address, String profilePictureUrl, String bloodType, String medicalConditions,
                String allergies, String medications, String dietaryPreference, int age) {

        if (dateOfBirth != null && dateOfBirth.after(new Date())) {
            throw new IllegalArgumentException("Date of Birth cannot be in the future.");
        }
        if (height != null && height <= 0) {
            throw new IllegalArgumentException("Height must be a positive number.");
        }
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive number.");
        }

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profilePictureUrl = profilePictureUrl;
        this.bloodType = bloodType;
        this.medicalConditions = medicalConditions;
        this.allergies = allergies;
        this.medications = medications;
        this.dietaryPreference = dietaryPreference;

        updateBMI();
        calculateAge();
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth != null && dateOfBirth.after(new Date())) {
            throw new IllegalArgumentException("Date of Birth cannot be in the future.");
        }
        this.dateOfBirth = dateOfBirth;
        calculateAge();
    }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) {
        if (height != null && height <= 0) {
            throw new IllegalArgumentException("Height must be a positive number.");
        }
        this.height = height;
        updateBMI();
    }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) {
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive number.");
        }
        this.weight = weight;
        updateBMI();
    }

    public Double getBmi() { return bmi; }

    private void updateBMI() {
        this.bmi = calculateBMI(this.height, this.weight);
    }

    private Double calculateBMI(Double height, Double weight) {
        if (height == null || weight == null || height <= 0) {
            return null;
        }
        double heightInMeters = height > 10 ? height / 100.0 : height;
        return weight / (heightInMeters * heightInMeters);
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

    public Integer getAge() { return age; }

    private void calculateAge() {
        if (this.dateOfBirth == null) {
            this.age = null;
            return;
        }

        Calendar dob = Calendar.getInstance();
        dob.setTime(this.dateOfBirth);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        this.age = age;
    }
}
