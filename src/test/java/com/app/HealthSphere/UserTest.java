package com.app.HealthSphere;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.app.HealthSphere.model.User;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

public class UserTest {

    public Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // Month is zero-based
        return calendar.getTime();
    }

    @Test
    public void testBMI_Calculation_ValidData() {
        User user = new User();
        user.setHeight(180.0);
        user.setWeight(75.0);
        assertThat(user.getBmi()).isEqualTo(23.15, within(0.01));
    }

    @Test
    public void testBMI_Calculation_HeightInMeters() {
        User user = new User();
        user.setHeight(1.8);
        user.setWeight(75.0);
        assertThat(user.getBmi()).isEqualTo(23.15, within(0.01));
    }

    @Test
    public void testBMI_Calculation_NullHeightOrWeight() {
        User user = new User();
        user.setHeight(null);
        user.setWeight(75.0);
        assertThat(user.getBmi()).isNull();

        user.setHeight(180.0);
        user.setWeight(null);
        assertThat(user.getBmi()).isNull();
    }

    @Test
    public void testBMI_Calculation_InvalidHeight() {
        User user = new User();
        user.setWeight(75.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setHeight(-10.0));
        assertThat(exception.getMessage()).isEqualTo("Height must be a positive number.");
    }

    @Test
    public void testBMI_Calculation_InvalidWeight() {
        User user = new User();
        user.setHeight(180.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setWeight(-50.0));
        assertThat(exception.getMessage()).isEqualTo("Weight must be a positive number.");
    }


    @Test
    public void testAge_Calculation_ValidDateOfBirth() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.MARCH, 25);
        User user = new User();
        user.setDateOfBirth(cal.getTime());

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int expectedAge = currentYear - 2000;

        assertThat(user.getAge()).isEqualTo(expectedAge);
    }


    @Test
    public void testAge_Calculation_NullDateOfBirth() {
        User user = new User();
        user.setDateOfBirth(null);
        assertThat(user.getAge()).isNull();
    }

    @Test
    public void testAge_Calculation_BoundaryDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 18);
        User user = new User();
        user.setDateOfBirth(cal.getTime());

        assertThat(user.getAge()).isEqualTo(18);
    }

    @Test
    public void testValidUserCreation() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, Calendar.JULY, 15);

        User user = new User(1L, "John", "Doe", cal.getTime(), "Male", 180.0, 75.0, null,
                "1234567890", "123 Main St", null, "O+", null, null, null, "Vegetarian", 0);

        assertThat(user.getUserId()).isEqualTo(1L);
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Doe");
        assertThat(user.getAge()).isGreaterThan(0);
        assertThat(user.getBmi()).isEqualTo(23.15, within(0.01));
    }

    @Test
    public void testConstructorInvalidHeight() {
        Date dob = getDate(1995, Calendar.JUNE, 15);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User(1L, "John", "Doe", dob, "Male", -180.0, 75.0, null,
                    "1234567890", "123 Street", "url", "O+", "None",
                    "None", "None", "Vegan", 0);
        });
        assertEquals("Height must be a positive number.", exception.getMessage());
    }

    @Test
    public void testConstructorInvalidWeight() {
        Date dob = getDate(1995, Calendar.JUNE, 15);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User(1L, "John", "Doe", dob, "Male", 180.0, -75.0, null,
                    "1234567890", "123 Street", "url", "O+", "None",
                    "None", "None", "Vegan", 0);
        });
        assertEquals("Weight must be a positive number.", exception.getMessage());
    }

    @Test
    public void testConstructorFutureDateOfBirth() {
        Date futureDate = getDate(2030, Calendar.JUNE, 15);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User(1L, "John", "Doe", futureDate, "Male", 180.0, 75.0, null,
                    "1234567890", "123 Street", "url", "O+", "None",
                    "None", "None", "Vegan", 0);
        });
        assertEquals("Date of Birth cannot be in the future.", exception.getMessage());
    }


    private static Offset<Double> within(double tolerance) {
        return Offset.offset(tolerance);
    }
}


