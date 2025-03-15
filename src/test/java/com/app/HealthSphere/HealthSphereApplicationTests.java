package com.app.HealthSphere;

import com.app.HealthSphere.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

class UserTests {

	@Test
	void testUserCreation() {
		User user = new User(1L, "John", "Doe", new Date(), "Male", 175.0, 70.0, null,
				"1234567890", "123 Street", null, "O+", null, null, null, "Vegan");

		assertNotNull(user);
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
	}

	@Test
	void testBMICalculation() {
		User user = new User();
		user.setHeight(175.0); // Height in cm
		user.setWeight(70.0);  // Weight in kg
		assertNotNull(user.getBmi());
		assertEquals(22.86, user.getBmi(), 0.01); // Approximate BMI
	}

	@Test
	void testAgeCalculation() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, Calendar.JANUARY, 1); // Example date of birth
		User user = new User();
		user.setDateOfBirth(calendar.getTime());
		assertNotNull(user.getAge());
		assertTrue(user.getAge() > 30); // Adapt based on the current year
	}

	@Test
	void testSettersAndGetters() {
		User user = new User();
		user.setFirstName("Alice");
		user.setLastName("Smith");
		user.setGender("Female");

		assertEquals("Alice", user.getFirstName());
		assertEquals("Smith", user.getLastName());
		assertEquals("Female", user.getGender());
	}

	@Test
	void testInvalidBMIInputs() {
		User user = new User();
		user.setHeight(0.0); // Invalid height
		user.setWeight(70.0);
		assertNull(user.getBmi());
	}
}
