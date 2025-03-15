package com.app.HealthSphere;

import com.app.HealthSphere.model.WorkoutRecommendations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;

class WorkoutRecommendationsTest {

    @Test
    void testWorkoutRecommendationsCreation() {
        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp updatedAt = Timestamp.from(Instant.now());

        WorkoutRecommendations workout = new WorkoutRecommendations(1, "Cardio Blast", "High-intensity cardio workout",
                "Cardio", 45, 500, "Medium", 4, "Treadmill", "http://example.com/video", createdAt, updatedAt);

        assertNotNull(workout);
        assertEquals(1, workout.getWorkoutId());
        assertEquals("Cardio Blast", workout.getWorkoutName());
        assertEquals("High-intensity cardio workout", workout.getWorkoutDescription());
        assertEquals("Cardio", workout.getExerciseType());
        assertEquals(45, workout.getDurationMinutes());
        assertEquals(500, workout.getCaloriesBurned());
        assertEquals("Medium", workout.getDifficultyLevel());
        assertEquals(4, workout.getFrequencyPerWeek());
        assertEquals("Treadmill", workout.getEquipmentNeeded());
        assertEquals("http://example.com/video", workout.getVideoUrl());
        assertEquals(createdAt, workout.getCreatedAt());
        assertEquals(updatedAt, workout.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        WorkoutRecommendations workout = new WorkoutRecommendations();

        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp updatedAt = Timestamp.from(Instant.now());

        workout.setWorkoutId(2);
        workout.setWorkoutName("Yoga Flow");
        workout.setWorkoutDescription("Relaxing yoga session");
        workout.setExerciseType("Yoga");
        workout.setDurationMinutes(30);
        workout.setCaloriesBurned(200);
        workout.setDifficultyLevel("Easy");
        workout.setFrequencyPerWeek(3);
        workout.setEquipmentNeeded("Yoga Mat");
        workout.setVideoUrl("https://example.com/yoga");
        workout.setCreatedAt(createdAt);
        workout.setUpdatedAt(updatedAt);

        assertEquals(2, workout.getWorkoutId());
        assertEquals("Yoga Flow", workout.getWorkoutName());
        assertEquals("Relaxing yoga session", workout.getWorkoutDescription());
        assertEquals("Yoga", workout.getExerciseType());
        assertEquals(30, workout.getDurationMinutes());
        assertEquals(200, workout.getCaloriesBurned());
        assertEquals("Easy", workout.getDifficultyLevel());
        assertEquals(3, workout.getFrequencyPerWeek());
        assertEquals("Yoga Mat", workout.getEquipmentNeeded());
        assertEquals("https://example.com/yoga", workout.getVideoUrl());
        assertEquals(createdAt, workout.getCreatedAt());
        assertEquals(updatedAt, workout.getUpdatedAt());
    }

    @Test
    void testInvalidWorkoutId() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setWorkoutId(-1));
        assertEquals("Workout ID must be a positive integer.", exception.getMessage());
    }

    @Test
    void testInvalidWorkoutName() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setWorkoutName(""));
        assertEquals("Workout Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidDurationMinutes() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setDurationMinutes(0));
        assertEquals("Duration Minutes must be a positive integer.", exception.getMessage());
    }

    @Test
    void testInvalidCaloriesBurned() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setCaloriesBurned(-50));
        assertEquals("Calories Burned cannot be negative.", exception.getMessage());
    }

    @Test
    void testInvalidDifficultyLevel() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setDifficultyLevel("Very Hard"));
        assertEquals("Difficulty Level must be 'Easy', 'Medium', or 'Hard'.", exception.getMessage());
    }

    @Test
    void testInvalidFrequencyPerWeek() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setFrequencyPerWeek(8));
        assertEquals("Frequency per Week must be between 1 and 7.", exception.getMessage());
    }

    @Test
    void testInvalidVideoUrl() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setVideoUrl("invalid-url"));
        assertEquals("Video URL must be a valid URL.", exception.getMessage());
    }

    @Test
    void testInvalidCreatedAtTimestamp() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setCreatedAt(null));
        assertEquals("CreatedAt timestamp cannot be null or in the future.", exception.getMessage());
    }

    @Test
    void testInvalidUpdatedAtTimestamp() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp pastUpdatedAt = Timestamp.from(Instant.now().minusSeconds(3600)); // One hour earlier

        workout.setCreatedAt(createdAt);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> workout.setUpdatedAt(pastUpdatedAt));
        assertEquals("UpdatedAt timestamp cannot be earlier than CreatedAt.", exception.getMessage());
    }
}
