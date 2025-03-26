package com.app.HealthSphere;
import com.app.HealthSphere.model.FitnessGoal;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class FitnessGoalTest {

    @Test
    public void testValidFitnessGoalCreation() {
        Date startDate = new Date();
        Date targetDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 7)); // 7 days later

        FitnessGoal goal = new FitnessGoal(1L, 1L, "Weight Loss", 70.0, 20.0, startDate, targetDate);
        assertNotNull(goal);
        assertEquals(1L, goal.getGoalId());
        assertEquals("Weight Loss", goal.getGoalType());
    }

    @Test
    public void testNegativeTargetWeightThrowsException() {
        Date startDate = new Date();
        Date targetDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 7));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FitnessGoal(1L, 1L, "Muscle Gain", -50.0, 15.0, startDate, targetDate);
        });
        assertEquals("Target weight must be greater than zero.", exception.getMessage());
    }

    @Test
    public void testNegativeTargetBodyFatThrowsException() {
        Date startDate = new Date();
        Date targetDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 7));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FitnessGoal(1L, 1L, "Muscle Gain", 80.0, -10.0, startDate, targetDate);
        });
        assertEquals("Target body fat percentage must be greater than zero.", exception.getMessage());
    }

    @Test
    public void testTargetDateBeforeStartDateThrowsException() {
        Date startDate = new Date();
        Date targetDate = new Date(startDate.getTime() - (1000 * 60 * 60 * 24 * 7)); // 7 days before

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FitnessGoal(1L, 1L, "Endurance", 70.0, 18.0, startDate, targetDate);
        });
        assertEquals("Target date cannot be before start date.", exception.getMessage());
    }

    @Test
    public void testSettersAndGetters() {
        Date startDate = new Date();
        Date targetDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 10));

        FitnessGoal goal = new FitnessGoal();
        goal.setGoalId(2L);
        goal.setUserId(3L);
        goal.setGoalType("Maintain Weight");
        goal.setTargetWeight(65.0);
        goal.setTargetBodyFat(22.0);
        goal.setStartDate(startDate);
        goal.setTargetDate(targetDate);

        assertEquals(2L, goal.getGoalId());
        assertEquals(3L, goal.getUserId());
        assertEquals("Maintain Weight", goal.getGoalType());
        assertEquals(65.0, goal.getTargetWeight());
        assertEquals(22.0, goal.getTargetBodyFat());
        assertEquals(startDate, goal.getStartDate());
        assertEquals(targetDate, goal.getTargetDate());
    }
}
