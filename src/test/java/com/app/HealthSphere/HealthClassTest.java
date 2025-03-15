package com.app.HealthSphere;

import com.app.HealthSphere.model.HealthLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class HealthLogTests {

    @Test
    void testHealthLogCreation() {
        try {
            HealthLog log = new HealthLog(1L, 2L, "Exercise", "Morning run", 300, 15.0, 50.0, 10.0,
                    60, "High", 70.0, 18.0, 120, 80, 70, 8, 2000);

            assertNotNull(log);
            assertEquals(1L, log.getLogId());
            assertEquals(2L, log.getUserId());
            assertEquals("Exercise", log.getLogType());
            assertEquals("Morning run", log.getDescription());
            assertEquals(300, log.getCalories());
            assertEquals(15.0, log.getProtein());
            assertEquals(50.0, log.getCarbohydrates());
            assertEquals(10.0, log.getFats());
            assertEquals(60, log.getDurationMinutes());
            assertEquals("High", log.getIntensity());
            assertEquals(70.0, log.getWeight());
            assertEquals(18.0, log.getBodyFat());
            assertEquals(120, log.getBloodPressureSystolic());
            assertEquals(80, log.getBloodPressureDiastolic());
            assertEquals(70, log.getHeartRate());
            assertEquals(8, log.getSleep());
            assertEquals(2000, log.getWaterIntake());
            assertNotNull(log.getLogDate());
        } catch (Exception e) {
            fail("Exception occurred during testHealthLogCreation: " + e.getMessage());
        }
    }

    @Test
    void testDefaultValues() {
        try {
            HealthLog log = new HealthLog();

            assertEquals(0, log.getCalories());
            assertEquals(0, log.getSleep());
            assertEquals(0, log.getWaterIntake());
            assertNotNull(log.getLogDate());
        } catch (Exception e) {
            fail("Exception occurred during testDefaultValues: " + e.getMessage());
        }
    }

    @Test
    void testSettersAndGetters() {
        try {
            HealthLog log = new HealthLog();

            log.setLogId(1L);
            log.setUserId(2L);
            log.setLogType("Diet");
            log.setDescription("Healthy diet");
            log.setCalories(500);
            log.setProtein(20.0);
            log.setCarbohydrates(40.0);
            log.setFats(10.0);
            log.setDurationMinutes(30);
            log.setIntensity("Medium");
            log.setWeight(65.0);
            log.setBodyFat(20.0);
            log.setBloodPressureSystolic(115);
            log.setBloodPressureDiastolic(75);
            log.setHeartRate(68);
            log.setSleep(7);
            log.setWaterIntake(1500);

            assertEquals(1L, log.getLogId());
            assertEquals(2L, log.getUserId());
            assertEquals("Diet", log.getLogType());
            assertEquals("Healthy diet", log.getDescription());
            assertEquals(500, log.getCalories());
            assertEquals(20.0, log.getProtein());
            assertEquals(40.0, log.getCarbohydrates());
            assertEquals(10.0, log.getFats());
            assertEquals(30, log.getDurationMinutes());
            assertEquals("Medium", log.getIntensity());
            assertEquals(65.0, log.getWeight());
            assertEquals(20.0, log.getBodyFat());
            assertEquals(115, log.getBloodPressureSystolic());
            assertEquals(75, log.getBloodPressureDiastolic());
            assertEquals(68, log.getHeartRate());
            assertEquals(7, log.getSleep());
            assertEquals(1500, log.getWaterIntake());
        } catch (Exception e) {
            fail("Exception occurred during testSettersAndGetters: " + e.getMessage());
        }
    }

    @Test
    void testNegativeCalories() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                HealthLog log = new HealthLog();
                log.setCalories(-100);
            } catch (Exception e) {
                System.err.println("Exception in testNegativeCalories: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test
    void testNegativeSleep() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                HealthLog log = new HealthLog();
                log.setSleep(-5);
            } catch (Exception e) {
                System.err.println("Exception in testNegativeSleep: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test
    void testNegativeWaterIntake() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                HealthLog log = new HealthLog();
                log.setWaterIntake(-1500);
            } catch (Exception e) {
                System.err.println("Exception in testNegativeWaterIntake: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test
    void testFutureLogDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                HealthLog log = new HealthLog();
                log.setLogDate(LocalDateTime.now().plusDays(1));
            } catch (Exception e) {
                System.err.println("Exception in testFutureLogDate: " + e.getMessage());
                throw e;
            }
        });
    }
}
