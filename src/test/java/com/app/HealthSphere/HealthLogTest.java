package com.app.HealthSphere;

import com.app.HealthSphere.model.HealthLog;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class HealthLogTest {

    @Test
    public void testHealthLogInitializationWithDefaultValues() {
        HealthLog healthLog = new HealthLog(1L, 101L, "Exercise", "Morning workout", null, null, null, null, null, "High", 75.0, 20.0, 120, 80, 70, null, null);

        assertEquals(1L, healthLog.getLogId());
        assertEquals(101L, healthLog.getUserId());
        assertEquals("Exercise", healthLog.getLogType());
        assertEquals("Morning workout", healthLog.getDescription());
        assertEquals(0, healthLog.getCalories());
        assertEquals(0.0, healthLog.getProtein());
        assertEquals(0.0, healthLog.getCarbohydrates());
        assertEquals(0.0, healthLog.getFats());
        assertEquals(0, healthLog.getDurationMinutes());
        assertEquals("High", healthLog.getIntensity());
        assertEquals(75.0, healthLog.getWeight());
        assertEquals(20.0, healthLog.getBodyFat());
        assertEquals(120, healthLog.getBloodPressureSystolic());
        assertEquals(80, healthLog.getBloodPressureDiastolic());
        assertEquals(70, healthLog.getHeartRate());
        assertEquals(0, healthLog.getSleep());
        assertEquals(0, healthLog.getWaterIntake());
        assertNotNull(healthLog.getLogDate());
    }

    @Test
    public void testHealthLogInitializationWithAllValues() {
        LocalDateTime now = LocalDateTime.now();
        HealthLog healthLog = new HealthLog(2L, 202L, "Meal", "Healthy breakfast", 300, 20.5, 50.0, 10.5, 30, "Medium", 70.5, 18.5, 130, 85, 75, 8, 2000);

        assertEquals(2L, healthLog.getLogId());
        assertEquals(202L, healthLog.getUserId());
        assertEquals("Meal", healthLog.getLogType());
        assertEquals("Healthy breakfast", healthLog.getDescription());
        assertEquals(300, healthLog.getCalories());
        assertEquals(20.5, healthLog.getProtein());
        assertEquals(50.0, healthLog.getCarbohydrates());
        assertEquals(10.5, healthLog.getFats());
        assertEquals(30, healthLog.getDurationMinutes());
        assertEquals("Medium", healthLog.getIntensity());
        assertEquals(70.5, healthLog.getWeight());
        assertEquals(18.5, healthLog.getBodyFat());
        assertEquals(130, healthLog.getBloodPressureSystolic());
        assertEquals(85, healthLog.getBloodPressureDiastolic());
        assertEquals(75, healthLog.getHeartRate());
        assertEquals(8, healthLog.getSleep());
        assertEquals(2000, healthLog.getWaterIntake());
    }

    @Test
    public void testSettersAndGetters() {
        HealthLog healthLog = new HealthLog();
        LocalDateTime now = LocalDateTime.now();

        healthLog.setLogId(10L);
        healthLog.setUserId(1001L);
        healthLog.setLogType("Exercise");
        healthLog.setDescription("Cardio session");
        healthLog.setCalories(500);
        healthLog.setProtein(25.0);
        healthLog.setCarbohydrates(60.0);
        healthLog.setFats(15.0);
        healthLog.setDurationMinutes(45);
        healthLog.setIntensity("High");
        healthLog.setWeight(80.0);
        healthLog.setBodyFat(22.0);
        healthLog.setBloodPressureSystolic(125);
        healthLog.setBloodPressureDiastolic(82);
        healthLog.setHeartRate(72);
        healthLog.setSleep(7);
        healthLog.setWaterIntake(1800);
        healthLog.setLogDate(now);

        assertEquals(10L, healthLog.getLogId());
        assertEquals(1001L, healthLog.getUserId());
        assertEquals("Exercise", healthLog.getLogType());
        assertEquals("Cardio session", healthLog.getDescription());
        assertEquals(500, healthLog.getCalories());
        assertEquals(25.0, healthLog.getProtein());
        assertEquals(60.0, healthLog.getCarbohydrates());
        assertEquals(15.0, healthLog.getFats());
        assertEquals(45, healthLog.getDurationMinutes());
        assertEquals("High", healthLog.getIntensity());
        assertEquals(80.0, healthLog.getWeight());
        assertEquals(22.0, healthLog.getBodyFat());
        assertEquals(125, healthLog.getBloodPressureSystolic());
        assertEquals(82, healthLog.getBloodPressureDiastolic());
        assertEquals(72, healthLog.getHeartRate());
        assertEquals(7, healthLog.getSleep());
        assertEquals(1800, healthLog.getWaterIntake());
        assertEquals(now, healthLog.getLogDate());
    }

    @Test
    public void testDefaultConstructor() {
        HealthLog healthLog = new HealthLog();
        assertNull(healthLog.getLogId());
        assertNull(healthLog.getUserId());
        assertNull(healthLog.getLogType());
        assertNull(healthLog.getDescription());
        assertNull(healthLog.getWeight());
        assertNull(healthLog.getBodyFat());
    }
}
