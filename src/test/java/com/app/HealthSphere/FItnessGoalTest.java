//package com.app.HealthSphere;
//
//import com.app.HealthSphere.model.FitnessGoal;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Date;
//import java.util.Calendar;
//
//class FitnessGoalTests {
//
//    @Test
//    void testFitnessGoalCreation() {
//        Calendar futureDate = Calendar.getInstance();
//        futureDate.add(Calendar.DATE, 30); // 30 days in the future
//
//        FitnessGoal goal = new FitnessGoal(1L, 2L, "Weight Loss", 70.0, 15.0, futureDate.getTime());
//
//        assertNotNull(goal);
//        assertEquals(1L, goal.getGoalId());
//        assertEquals(2L, goal.getUserId());
//        assertEquals("Weight Loss", goal.getGoalType());
//        assertEquals(70.0, goal.getTargetWeight());
//        assertEquals(15.0, goal.getTargetBodyFat());
//        assertEquals(futureDate.getTime(), goal.getTargetDate());
//    }
//
//    @Test
//    void testSettersAndGetters() {
//        FitnessGoal goal = new FitnessGoal();
//
//        Calendar futureDate = Calendar.getInstance();
//        futureDate.add(Calendar.DATE, 60); // 60 days in the future
//
//        goal.setGoalId(1L);
//        goal.setUserId(2L);
//        goal.setGoalType("Build Muscle");
//        goal.setTargetWeight(80.0);
//        goal.setTargetBodyFat(10.0);
//        goal.setTargetDate(futureDate.getTime());
//
//        assertEquals(1L, goal.getGoalId());
//        assertEquals(2L, goal.getUserId());
//        assertEquals("Build Muscle", goal.getGoalType());
//        assertEquals(80.0, goal.getTargetWeight());
//        assertEquals(10.0, goal.getTargetBodyFat());
//        assertEquals(futureDate.getTime(), goal.getTargetDate());
//    }
//
//    @Test
//    void testInvalidUserId() {
//        FitnessGoal goal = new FitnessGoal();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setUserId(-1L));
//        assertEquals("Invalid userId: It must be a positive number.", exception.getMessage());
//    }
//
//    @Test
//    void testInvalidGoalType() {
//        FitnessGoal goal = new FitnessGoal();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setGoalType(""));
//        assertEquals("Invalid goalType: It cannot be null or empty.", exception.getMessage());
//    }
//
//    @Test
//    void testInvalidTargetWeight() {
//        FitnessGoal goal = new FitnessGoal();
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setTargetWeight(-5.0));
//        assertEquals("Invalid targetWeight: It must be a positive number.", exception.getMessage());
//    }
//
//    @Test
//    void testInvalidTargetBodyFat() {
//        FitnessGoal goal = new FitnessGoal();
//
//        Exception lowException = assertThrows(IllegalArgumentException.class, () -> goal.setTargetBodyFat(-5.0));
//        assertEquals("Invalid targetBodyFat: It must be between 0 and 100%.", lowException.getMessage());
//
//        Exception highException = assertThrows(IllegalArgumentException.class, () -> goal.setTargetBodyFat(105.0));
//        assertEquals("Invalid targetBodyFat: It must be between 0 and 100%.", highException.getMessage());
//    }
//
//    @Test
//    void testInvalidTargetDate() {
//        FitnessGoal goal = new FitnessGoal();
//        Calendar pastDate = Calendar.getInstance();
//        pastDate.add(Calendar.DATE, -1); // Yesterday
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setTargetDate(pastDate.getTime()));
//        assertEquals("Invalid targetDate: It must be a future date.", exception.getMessage());
//    }
//
//    @Test
//    void testValidationsOnConstructor() {
//        Calendar pastDate = Calendar.getInstance();
//        pastDate.add(Calendar.DATE, -1); // Yesterday
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                new FitnessGoal(1L, -2L, "", -70.0, 110.0, pastDate.getTime())
//        );
//        assertEquals("User ID must be a positive number.", exception.getMessage());
//    }
//}
