package com.app.HealthSphere;
import com.app.HealthSphere.model.WorkoutRecommendations;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Timestamp;

public class WorkoutRecommendationsTest {

    @Test
    public void testParameterizedConstructor() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        WorkoutRecommendations workout = new WorkoutRecommendations(
                1, 101, 202, "Morning Yoga", "A relaxing yoga session", "Yoga",
                45, 200, "Beginner", 3, "Mat", "http://example.com/video", createdAt, updatedAt
        );

        assertThat(workout.getWorkoutId()).isEqualTo(1);
        assertThat(workout.getUserId()).isEqualTo(101);
        assertThat(workout.getGoalId()).isEqualTo(202);
        assertThat(workout.getWorkoutName()).isEqualTo("Morning Yoga");
        assertThat(workout.getWorkoutDescription()).isEqualTo("A relaxing yoga session");
        assertThat(workout.getExerciseType()).isEqualTo("Yoga");
        assertThat(workout.getDurationMinutes()).isEqualTo(45);
        assertThat(workout.getCaloriesBurned()).isEqualTo(200);
        assertThat(workout.getDifficultyLevel()).isEqualTo("Beginner");
        assertThat(workout.getFrequencyPerWeek()).isEqualTo(3);
        assertThat(workout.getEquipmentNeeded()).isEqualTo("Mat");
        assertThat(workout.getVideoUrl()).isEqualTo("http://example.com/video");
        assertThat(workout.getCreatedAt()).isEqualTo(createdAt);
        assertThat(workout.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void testDefaultConstructorAndSetters() {
        WorkoutRecommendations workout = new WorkoutRecommendations();

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        workout.setWorkoutId(2);
        workout.setUserId(102);
        workout.setGoalId(203);
        workout.setWorkoutName("Evening Cardio");
        workout.setWorkoutDescription("High-intensity cardio workout");
        workout.setExerciseType("Cardio");
        workout.setDurationMinutes(60);
        workout.setCaloriesBurned(400);
        workout.setDifficultyLevel("Intermediate");
        workout.setFrequencyPerWeek(4);
        workout.setEquipmentNeeded("Treadmill");
        workout.setVideoUrl("http://example.com/cardio");
        workout.setCreatedAt(createdAt);
        workout.setUpdatedAt(updatedAt);

        assertThat(workout.getWorkoutId()).isEqualTo(2);
        assertThat(workout.getUserId()).isEqualTo(102);
        assertThat(workout.getGoalId()).isEqualTo(203);
        assertThat(workout.getWorkoutName()).isEqualTo("Evening Cardio");
        assertThat(workout.getWorkoutDescription()).isEqualTo("High-intensity cardio workout");
        assertThat(workout.getExerciseType()).isEqualTo("Cardio");
        assertThat(workout.getDurationMinutes()).isEqualTo(60);
        assertThat(workout.getCaloriesBurned()).isEqualTo(400);
        assertThat(workout.getDifficultyLevel()).isEqualTo("Intermediate");
        assertThat(workout.getFrequencyPerWeek()).isEqualTo(4);
        assertThat(workout.getEquipmentNeeded()).isEqualTo("Treadmill");
        assertThat(workout.getVideoUrl()).isEqualTo("http://example.com/cardio");
        assertThat(workout.getCreatedAt()).isEqualTo(createdAt);
        assertThat(workout.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void testGettersAndSetters() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        workout.setWorkoutId(1);
        workout.setUserId(1001);
        workout.setGoalId(200);
        workout.setWorkoutName("Strength Training");
        workout.setWorkoutDescription("Build muscle and endurance");
        workout.setExerciseType("Strength");
        workout.setDurationMinutes(60);
        workout.setCaloriesBurned(400);
        workout.setDifficultyLevel("Advanced");
        workout.setFrequencyPerWeek(5);
        workout.setEquipmentNeeded("Dumbbells");
        workout.setVideoUrl("http://example.com/video");
        workout.setCreatedAt(now);
        workout.setUpdatedAt(now);

        assertThat(workout.getWorkoutId()).isEqualTo(1);
        assertThat(workout.getUserId()).isEqualTo(1001);
        assertThat(workout.getGoalId()).isEqualTo(200);
        assertThat(workout.getWorkoutName()).isEqualTo("Strength Training");
        assertThat(workout.getWorkoutDescription()).isEqualTo("Build muscle and endurance");
        assertThat(workout.getExerciseType()).isEqualTo("Strength");
        assertThat(workout.getDurationMinutes()).isEqualTo(60);
        assertThat(workout.getCaloriesBurned()).isEqualTo(400);
        assertThat(workout.getDifficultyLevel()).isEqualTo("Advanced");
        assertThat(workout.getFrequencyPerWeek()).isEqualTo(5);
        assertThat(workout.getEquipmentNeeded()).isEqualTo("Dumbbells");
        assertThat(workout.getVideoUrl()).isEqualTo("http://example.com/video");
        assertThat(workout.getCreatedAt()).isEqualTo(now);
        assertThat(workout.getUpdatedAt()).isEqualTo(now);
    }

    @Test
    public void testNullGoalId() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        workout.setGoalId(null);
        assertThat(workout.getGoalId()).isNull();
    }

    @Test
    public void testExtremeCaloriesBurned() {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        workout.setCaloriesBurned(Integer.MAX_VALUE);
        assertThat(workout.getCaloriesBurned()).isEqualTo(Integer.MAX_VALUE);
    }
}
