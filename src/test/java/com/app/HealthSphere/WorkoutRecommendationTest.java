package com.app.HealthSphere.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutRecommendationsTest {

    @Test
    void testDefaultConstructor() {
        // Test the default constructor
        WorkoutRecommendations workout = new WorkoutRecommendations();
        assertNotNull(workout, "Default constructor should create a non-null object.");
    }

    @Test
    void testParameterizedConstructor() {
        // Set up test data
        Timestamp now = new Timestamp(System.currentTimeMillis());
        WorkoutRecommendations workout = new WorkoutRecommendations(
                1, 1001, 201, "Morning Yoga", "A refreshing morning routine", "Yoga",
                30, 200, "Beginner", 5, "Yoga Mat", "http://example.com/video", now, now
        );

        // Verify the constructor assigns values correctly
        assertEquals(1, workout.getWorkoutId());
        assertEquals(1001, workout.getUserId());
        assertEquals(201, workout.getGoalId());
        assertEquals("Morning Yoga", workout.getWorkoutName());
        assertEquals("A refreshing morning routine", workout.getWorkoutDescription());
        assertEquals("Yoga", workout.getExerciseType());
        assertEquals(30, workout.getDurationMinutes());
        assertEquals(200, workout.getCaloriesBurned());
        assertEquals("Beginner", workout.getDifficultyLevel());
        assertEquals(5, workout.getFrequencyPerWeek());
        assertEquals("Yoga Mat", workout.getEquipmentNeeded());
        assertEquals("http://example.com/video", workout.getVideoUrl());
        assertEquals(now, workout.getCreatedAt());
        assertEquals(now, workout.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        // Create an instance of WorkoutRecommendations
        WorkoutRecommendations workout = new WorkoutRecommendations();

        // Set values using setters
        workout.setWorkoutId(2);
        workout.setUserId(1002);
        workout.setGoalId(202);
        workout.setWorkoutName("Cardio Blast");
        workout.setWorkoutDescription("High-energy cardio session.");
        workout.setExerciseType("Cardio");
        workout.setDurationMinutes(45);
        workout.setCaloriesBurned(400);
        workout.setDifficultyLevel("Intermediate");
        workout.setFrequencyPerWeek(3);
        workout.setEquipmentNeeded("Treadmill");
        workout.setVideoUrl("http://example.com/cardio");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        workout.setCreatedAt(now);
        workout.setUpdatedAt(now);

        // Verify values using getters
        assertEquals(2, workout.getWorkoutId());
        assertEquals(1002, workout.getUserId());
        assertEquals(202, workout.getGoalId());
        assertEquals("Cardio Blast", workout.getWorkoutName());
        assertEquals("High-energy cardio session.", workout.getWorkoutDescription());
        assertEquals("Cardio", workout.getExerciseType());
        assertEquals(45, workout.getDurationMinutes());
        assertEquals(400, workout.getCaloriesBurned());
        assertEquals("Intermediate", workout.getDifficultyLevel());
        assertEquals(3, workout.getFrequencyPerWeek());
        assertEquals("Treadmill", workout.getEquipmentNeeded());
        assertEquals("http://example.com/cardio", workout.getVideoUrl());
        assertEquals(now, workout.getCreatedAt());
        assertEquals(now, workout.getUpdatedAt());
    }

    @Test
    void testBoundaryValues() {
        // Test boundary values for numerical fields
        Timestamp now = new Timestamp(System.currentTimeMillis());
        WorkoutRecommendations workout = new WorkoutRecommendations(
                Integer.MAX_VALUE, Integer.MIN_VALUE, null, "", "",
                "Low Intensity", 0, 0, "Beginner", 0, null, "",
                now, now
        );

        // Assertions for boundary values
        assertEquals(Integer.MAX_VALUE, workout.getWorkoutId());
        assertEquals(Integer.MIN_VALUE, workout.getUserId());
        assertNull(workout.getGoalId());
        assertEquals("", workout.getWorkoutName());
        assertEquals("", workout.getWorkoutDescription());
        assertEquals("Low Intensity", workout.getExerciseType());
        assertEquals(0, workout.getDurationMinutes());
        assertEquals(0, workout.getCaloriesBurned());
        assertEquals("Beginner", workout.getDifficultyLevel());
        assertEquals(0, workout.getFrequencyPerWeek());
        assertNull(workout.getEquipmentNeeded());
        assertEquals("", workout.getVideoUrl());
    }

    @Test
    void testExceptionalCases() {
        WorkoutRecommendations workout = new WorkoutRecommendations();

        // Test invalid duration
        Exception invalidDurationException = assertThrows(IllegalArgumentException.class, () -> {
            workout.setDurationMinutes(-30);
        });
        assertEquals("Duration cannot be negative.", invalidDurationException.getMessage());

        // Test invalid calories burned
        Exception invalidCaloriesException = assertThrows(IllegalArgumentException.class, () -> {
            workout.setCaloriesBurned(-500);
        });
        assertEquals("Calories burned cannot be negative.", invalidCaloriesException.getMessage());

        // Test invalid frequency per week
        Exception invalidFrequencyException = assertThrows(IllegalArgumentException.class, () -> {
            workout.setFrequencyPerWeek(-2);
        });
        assertEquals("Frequency per week cannot be negative.", invalidFrequencyException.getMessage());
    }
}
