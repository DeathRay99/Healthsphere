package com.app.HealthSphere;
import static org.assertj.core.api.Assertions.assertThat;

import com.app.HealthSphere.model.Consultant;
import org.junit.jupiter.api.Test;

public class ConsultantTest {

    @Test
    public void testConstructorAndGetters() {
        Consultant consultant = new Consultant(1, "John", "Doe", "Nutritionist", "1234567890", "john.doe@example.com", "Experienced in clinical nutrition.");

        assertThat(consultant.getConsultantId()).isEqualTo(1);
        assertThat(consultant.getFirstName()).isEqualTo("John");
        assertThat(consultant.getLastName()).isEqualTo("Doe");
        assertThat(consultant.getDesignation()).isEqualTo("Nutritionist");
        assertThat(consultant.getPhoneNo()).isEqualTo("1234567890");
        assertThat(consultant.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(consultant.getNotes()).isEqualTo("Experienced in clinical nutrition.");
    }

    @Test
    public void testSetters() {
        Consultant consultant = new Consultant();
        consultant.setConsultantId(2);
        consultant.setFirstName("Jane");
        consultant.setLastName("Smith");
        consultant.setDesignation("Fitness Trainer");
        consultant.setPhoneNo("9876543210");
        consultant.setEmail("jane.smith@example.com");
        consultant.setNotes("Specializes in strength training.");

        assertThat(consultant.getConsultantId()).isEqualTo(2);
        assertThat(consultant.getFirstName()).isEqualTo("Jane");
        assertThat(consultant.getLastName()).isEqualTo("Smith");
        assertThat(consultant.getDesignation()).isEqualTo("Fitness Trainer");
        assertThat(consultant.getPhoneNo()).isEqualTo("9876543210");
        assertThat(consultant.getEmail()).isEqualTo("jane.smith@example.com");
        assertThat(consultant.getNotes()).isEqualTo("Specializes in strength training.");
    }

    @Test
    public void testToString() {
        Consultant consultant = new Consultant(3, "Alice", "Johnson", "Dietitian", "1122334455", "alice.johnson@example.com", "Certified dietitian with 10 years experience.");

        String expectedString = "Consultant{consultantId=3, firstName='Alice', lastName='Johnson', designation='Dietitian', phoneNo='1122334455', email='alice.johnson@example.com', notes='Certified dietitian with 10 years experience.'}";

        assertThat(consultant.toString()).isEqualTo(expectedString);
    }

    @Test
    public void testInvalidPhoneNumber() {
        Consultant consultant = new Consultant();
        consultant.setPhoneNo("12345");
        assertThat(consultant.getPhoneNo()).isEqualTo("12345");
    }

    @Test
    public void testNullValues() {
        Consultant consultant = new Consultant(4, null, null, null, null, null, null);

        assertThat(consultant.getFirstName()).isNull();
        assertThat(consultant.getLastName()).isNull();
        assertThat(consultant.getDesignation()).isNull();
        assertThat(consultant.getPhoneNo()).isNull();
        assertThat(consultant.getEmail()).isNull();
        assertThat(consultant.getNotes()).isNull();
    }
}
