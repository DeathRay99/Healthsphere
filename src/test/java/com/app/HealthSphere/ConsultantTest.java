package com.app.HealthSphere;

import com.app.HealthSphere.model.Consultant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultantTests {

    @Test
    void testConsultantCreation() {
        Consultant consultant = new Consultant(1, "John", "Doe", "Health Advisor", "9876543210", "john.doe@example.com", "Experienced consultant");

        assertNotNull(consultant);
        assertEquals(1, consultant.getConsultantId());
        assertEquals("John", consultant.getFirstName());
        assertEquals("Doe", consultant.getLastName());
        assertEquals("Health Advisor", consultant.getDesignation());
        assertEquals("9876543210", consultant.getPhoneNo());
        assertEquals("john.doe@example.com", consultant.getEmail());
        assertEquals("Experienced consultant", consultant.getNotes());
    }

    @Test
    void testSettersAndGetters() {
        Consultant consultant = new Consultant();
        consultant.setConsultantId(2);
        consultant.setFirstName("Jane");
        consultant.setLastName("Smith");
        consultant.setDesignation("Dietitian");
        consultant.setPhoneNo("1234567890");
        consultant.setEmail("jane.smith@example.com");
        consultant.setNotes("Specialist in nutrition");

        assertEquals(2, consultant.getConsultantId());
        assertEquals("Jane", consultant.getFirstName());
        assertEquals("Smith", consultant.getLastName());
        assertEquals("Dietitian", consultant.getDesignation());
        assertEquals("1234567890", consultant.getPhoneNo());
        assertEquals("jane.smith@example.com", consultant.getEmail());
        assertEquals("Specialist in nutrition", consultant.getNotes());
    }

    @Test
    void testInvalidConsultantId() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setConsultantId(-1));
        assertEquals("Consultant ID must be a positive integer.", exception.getMessage());
    }

    @Test
    void testInvalidFirstName() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setFirstName(""));
        assertEquals("First name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidLastName() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setLastName(" "));
        assertEquals("Last name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidDesignation() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setDesignation(null));
        assertEquals("Designation cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidPhoneNo() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setPhoneNo("12345"));
        assertEquals("Phone number must be a 10-digit numeric value.", exception.getMessage());
    }

    @Test
    void testInvalidEmail() {
        Consultant consultant = new Consultant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> consultant.setEmail("invalid-email"));
        assertEquals("Invalid email address format.", exception.getMessage());
    }
}
