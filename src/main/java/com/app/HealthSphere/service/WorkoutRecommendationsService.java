package com.app.HealthSphere.service;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.model.WorkoutRecommendations;
import com.app.HealthSphere.repository.WorkoutRecommendationsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class WorkoutRecommendationsService {

    private final WorkoutRecommendationsRepository workoutRecommendationsRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

//    @Value("${gemini.api.url}")
//    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Autowired
    public WorkoutRecommendationsService(
            WorkoutRecommendationsRepository workoutRecommendationsRepository,
            JdbcTemplate jdbcTemplate,
            ObjectMapper objectMapper,
            WebClient.Builder webClientBuilder,
            @Value("${gemini.api.url}") String geminiApiUrl) {
        this.workoutRecommendationsRepository = workoutRecommendationsRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(geminiApiUrl).build();
    }

    // Original CRUD operations

    // Save workout recommendation
    public int saveWorkoutRecommendation(WorkoutRecommendations workoutRecommendations) {
        return workoutRecommendationsRepository.save(workoutRecommendations);
    }

    // Save multiple workout recommendations
//    public List<Integer> saveWorkoutRecommendations(List<WorkoutRecommendations> recommendations) {
//        List<Integer> savedIds = new ArrayList<>();
//        for (WorkoutRecommendations recommendation : recommendations) {
//            savedIds.add(saveWorkoutRecommendation(recommendation));
//        }
//        return savedIds;
//    }

    // Find all workout recommendations
    public List<WorkoutRecommendations> findAllWorkoutRecommendations() {
        return workoutRecommendationsRepository.findAll();
    }

    // Find workout recommendation by ID
    public WorkoutRecommendations findWorkoutRecommendationById(int workoutId) {
        return workoutRecommendationsRepository.findById(workoutId);
    }

    // Update workout recommendation
    public int updateWorkoutRecommendation(WorkoutRecommendations workoutRecommendations) {
        return workoutRecommendationsRepository.update(workoutRecommendations);
    }

    // Delete workout recommendation by ID
    public int deleteWorkoutRecommendationById(int workoutId) {
        return workoutRecommendationsRepository.deleteById(workoutId);
    }

    // AI-powered workout generation methods



    // New method to generate multiple workouts with a single API call
    public List<WorkoutRecommendations> generateMultipleWorkoutRecommendations(int userId, int goalId) {
        Map<String, Object> userProfile = fetchUserProfile(userId, goalId);

        // Build a prompt that requests all four workouts at once
        String prompt = buildMultiWorkoutGeminiPrompt(userProfile);

        // Make a single API call
        String aiResponse = callGeminiApi(prompt);

        // Parse the AI response to get all four workouts
        List<WorkoutRecommendations> recommendations = parseMultipleWorkoutsResponse(aiResponse, userId, goalId);

        // Save all recommendations

        for (WorkoutRecommendations recommendation : recommendations) {
            saveWorkoutRecommendation(recommendation);
        }

        return recommendations;
    }

    private Map<String, Object> fetchUserProfile(int userId, int goalId) {
        String sql = """
            SELECT u.gender, u.age, u.height, u.weight,\s
                   u.bmi,
                   f.goal_id, f.goal_type, f.target_weight, f.target_body_fat, f.target_date,\s
                   u.medical_conditions
            FROM Users u\s
            JOIN FitnessGoals f ON u.user_id = f.user_id\s
            WHERE u.user_id = ? AND f.goal_id = ?
       \s""";

        return jdbcTemplate.queryForMap(sql, userId, goalId);
    }

    private String buildMultiWorkoutGeminiPrompt(Map<String, Object> userProfile) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Generate four different personalized workout recommendations based on the following user profile:\n\n");

        promptBuilder.append("### User Profile\n");
        promptBuilder.append("- Gender: ").append(userProfile.get("gender")).append("\n");
        promptBuilder.append("- Age: ").append(userProfile.get("age")).append("\n");
        promptBuilder.append("- Height: ").append(userProfile.get("height")).append(" cm\n");
        promptBuilder.append("- Weight: ").append(userProfile.get("weight")).append(" kg\n");
        promptBuilder.append("- BMI: ").append(userProfile.get("bmi")).append("\n");
        promptBuilder.append("- Medical Conditions: ").append(userProfile.get("medical_conditions")).append("\n\n");

        promptBuilder.append("### Fitness Goals\n");
        promptBuilder.append("- Goal Type: ").append(userProfile.get("goal_type")).append("\n");
        promptBuilder.append("- Target Weight: ").append(userProfile.get("target_weight")).append(" kg\n");
        promptBuilder.append("- Target Body Fat: ").append(userProfile.get("target_body_fat")).append("%\n");
        promptBuilder.append("- Target Date: ").append(userProfile.get("target_date")).append("\n\n");

        promptBuilder.append("### Recommendation Requirements\n");
        promptBuilder.append("Create four different workout recommendations:\n");
        promptBuilder.append("1. One workout with difficulty_level = 'Beginner'\n");
        promptBuilder.append("2. One workout with difficulty_level = 'Intermediate'\n");
        promptBuilder.append("3. One workout with difficulty_level = 'Advanced'\n");
        promptBuilder.append("4. One additional workout of any difficulty level ('Beginner', 'Intermediate', 'Advanced') that's different from the others\n\n");

        promptBuilder.append("Provide a structured response in JSON format as an array of four workout objects. Each workout should have these exact keys: ");
        promptBuilder.append("workout_name (string), workout_description (string), exercise_type (string) ENUM('Cardio', 'Strength', 'Flexibility', 'Balance', 'HIIT', 'Low Impact'), ");
        promptBuilder.append("duration_minutes (integer), calories_burned (integer), difficulty_level (string) ENUM('Beginner', 'Intermediate', 'Advanced'), ");
        promptBuilder.append("frequency_per_week (integer), equipment_needed (string), video_url (string).\n\n");
        promptBuilder.append("Format your response as a valid JSON array enclosed in square brackets, containing exactly 4 workout objects. ");
        promptBuilder.append("For example: [{\"workout_name\": \"HIIT Cardio\", \"difficulty_level\": \"Beginner\", ...}, {\"workout_name\": \"Strength Training\", \"difficulty_level\": \"Intermediate\", ...}, ...]");

        return promptBuilder.toString();
    }


    private String callGeminiApi(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        requestBody.put("contents", List.of(content));

        return webClient.post()
                .uri("?key=" + geminiApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(this::extractTextFromGeminiResponse)
                .block();
    }

    private String extractTextFromGeminiResponse(Map<String, Object> response) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                if (parts != null && !parts.isEmpty()) {
                    return (String) parts.get(0).get("text");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from Gemini API response", e);
        }
        return null;
    }

    // Parse the response for multiple workouts
    private List<WorkoutRecommendations> parseMultipleWorkoutsResponse(String aiResponse, int userId, int goalId) {
        List<WorkoutRecommendations> recommendations = new ArrayList<>();

        try {
            // Try to extract JSON array from the response
            String jsonContent = extractJsonArrayFromText(aiResponse);
            JsonNode jsonArray = objectMapper.readTree(jsonContent);

            // If it's not an array, try to wrap a single object as an array
            if (!jsonArray.isArray()) {
                if (jsonArray.isObject()) {
                    // Create a new array node with the single object
                    JsonNode singleObject = jsonArray;
                    jsonArray = objectMapper.createArrayNode().add(singleObject);
                } else {
                    throw new JsonProcessingException("Response is not a valid JSON array or object") {};
                }
            }

            // Process each workout in the array
            for (JsonNode workoutNode : jsonArray) {
                WorkoutRecommendations recommendation = createWorkoutFromJsonNode(workoutNode, userId);
                recommendation.setGoalId(goalId);
                recommendations.add(recommendation);
            }

            // If we have fewer than 4 workouts, add default ones to reach 4
            String[] difficulties = {"Beginner", "Intermediate", "Advanced"};
            int index = 0;

            while (recommendations.size() < 4) {
                WorkoutRecommendations defaultWorkout = createDefaultWorkout(userId, goalId, difficulties[index % 3]);
                recommendations.add(defaultWorkout);
                index++;
            }

            // Ensure we have exactly one of each difficulty level in the first three positions
            boolean hasBeginner = false;
            boolean hasIntermediate = false;
            boolean hasAdvanced = false;

            for (int i = 0; i < 3; i++) {
                WorkoutRecommendations workout = recommendations.get(i);
                String difficulty = workout.getDifficultyLevel();

                if ("Beginner".equals(difficulty)) {
                    hasBeginner = true;
                } else if ("Intermediate".equals(difficulty)) {
                    hasIntermediate = true;
                } else if ("Advanced".equals(difficulty)) {
                    hasAdvanced = true;
                }
            }

            // Replace workouts if needed to ensure all difficulty levels
            if (!hasBeginner) {
                recommendations.set(0, createDefaultWorkout(userId, goalId, "Beginner"));
            }
            if (!hasIntermediate) {
                recommendations.set(1, createDefaultWorkout(userId, goalId, "Intermediate"));
            }
            if (!hasAdvanced) {
                recommendations.set(2, createDefaultWorkout(userId, goalId, "Advanced"));
            }

            // Make sure we have exactly 4 workouts
            while (recommendations.size() > 4) {
                recommendations.remove(recommendations.size() - 1);
            }

        } catch (Exception e) {
            // If parsing fails, create four default recommendations
            recommendations.clear();
            recommendations.add(createDefaultWorkout(userId, goalId, "Beginner"));
            recommendations.add(createDefaultWorkout(userId, goalId, "Intermediate"));
            recommendations.add(createDefaultWorkout(userId, goalId, "Advanced"));
            recommendations.add(createDefaultWorkout(userId, goalId, "Intermediate")); // Fourth workout with any difficulty
        }

        return recommendations;
    }

    private WorkoutRecommendations createDefaultWorkout(int userId, int goalId, String difficultyLevel) {
        WorkoutRecommendations workout = new WorkoutRecommendations();
        workout.setUserId(userId);
        workout.setGoalId(goalId);

        Timestamp now = Timestamp.from(Instant.now());
        workout.setCreatedAt(now);
        workout.setUpdatedAt(now);

        // Set default values based on difficulty level
        workout.setDifficultyLevel(difficultyLevel);

        switch (difficultyLevel) {
            case "Beginner":
                workout.setWorkoutName("Basic Fitness Starter");
                workout.setWorkoutDescription("A gentle introduction to fitness designed for beginners with minimal equipment requirements.");
                workout.setExerciseType("Low Impact");
                workout.setDurationMinutes(20);
                workout.setCaloriesBurned(150);
                workout.setFrequencyPerWeek(3);
                workout.setEquipmentNeeded("None or minimal (resistance band)");
                break;

            case "Intermediate":
                workout.setWorkoutName("Progressive Circuit Training");
                workout.setWorkoutDescription("A balanced workout mixing cardio and strength training for those with some fitness experience.");
                workout.setExerciseType("Mixed");
                workout.setDurationMinutes(40);
                workout.setCaloriesBurned(300);
                workout.setFrequencyPerWeek(4);
                workout.setEquipmentNeeded("Dumbbells, resistance bands");
                break;

            case "Advanced":
                workout.setWorkoutName("High Intensity Performance");
                workout.setWorkoutDescription("Challenging workout designed for those with significant fitness experience to push limits and maximize results.");
                workout.setExerciseType("HIIT");
                workout.setDurationMinutes(60);
                workout.setCaloriesBurned(500);
                workout.setFrequencyPerWeek(5);
                workout.setEquipmentNeeded("Full gym equipment");
                break;

            default:
                workout.setWorkoutName("General Fitness Plan");
                workout.setWorkoutDescription("A balanced workout plan to improve overall fitness.");
                workout.setExerciseType("Mixed");
                workout.setDurationMinutes(30);
                workout.setCaloriesBurned(250);
                workout.setFrequencyPerWeek(3);
                workout.setEquipmentNeeded("Basic home equipment");
        }

        workout.setVideoUrl("");

        return workout;
    }

    private WorkoutRecommendations createWorkoutFromJsonNode(JsonNode jsonNode, int userId) {
        WorkoutRecommendations recommendation = new WorkoutRecommendations();
        recommendation.setUserId(userId);

        // Set default values for all fields first to avoid nulls
        recommendation.setWorkoutName("");
        recommendation.setWorkoutDescription("");
        recommendation.setExerciseType("");
        recommendation.setDurationMinutes(0);
        recommendation.setCaloriesBurned(0);
        recommendation.setDifficultyLevel("");
        recommendation.setFrequencyPerWeek(0);
        recommendation.setEquipmentNeeded("");
        recommendation.setVideoUrl("");

        // Now override with actual values if they exist
        if (jsonNode.has("workout_name")) {
            recommendation.setWorkoutName(jsonNode.get("workout_name").asText());
        }
        if (jsonNode.has("workout_description")) {
            recommendation.setWorkoutDescription(jsonNode.get("workout_description").asText());
        }
        if (jsonNode.has("exercise_type")) {
            recommendation.setExerciseType(jsonNode.get("exercise_type").asText());
        }
        if (jsonNode.has("duration_minutes")) {
            try {
                recommendation.setDurationMinutes(jsonNode.get("duration_minutes").asInt());
            } catch (Exception e) {
                // Handle case where value might be a string
                String value = jsonNode.get("duration_minutes").asText();
                recommendation.setDurationMinutes(Integer.parseInt(value.replaceAll("[^0-9]", "")));
            }
        }
        if (jsonNode.has("calories_burned")) {
            try {
                recommendation.setCaloriesBurned(jsonNode.get("calories_burned").asInt());
            } catch (Exception e) {
                // Handle case where value might be a string
                String value = jsonNode.get("calories_burned").asText();
                recommendation.setCaloriesBurned(Integer.parseInt(value.replaceAll("[^0-9]", "")));
            }
        }
        if (jsonNode.has("difficulty_level")) {
            recommendation.setDifficultyLevel(jsonNode.get("difficulty_level").asText());
        }
        if (jsonNode.has("frequency_per_week")) {
            try {
                recommendation.setFrequencyPerWeek(jsonNode.get("frequency_per_week").asInt());
            } catch (Exception e) {
                // Handle case where value might be a string
                String value = jsonNode.get("frequency_per_week").asText();
                recommendation.setFrequencyPerWeek(Integer.parseInt(value.replaceAll("[^0-9]", "")));
            }
        }
        if (jsonNode.has("equipment_needed")) {
            recommendation.setEquipmentNeeded(jsonNode.get("equipment_needed").asText());
        }
        if (jsonNode.has("video_url")) {
            recommendation.setVideoUrl(jsonNode.get("video_url").asText());
        }

        // Set timestamps for created_at and updated_at
        Timestamp now = Timestamp.from(Instant.now());
        recommendation.setCreatedAt(now);
        recommendation.setUpdatedAt(now);

        return recommendation;
    }

    /**
     * Helper method to extract JSON array from a text that might contain additional content
     */
    private String extractJsonArrayFromText(String text) {
        // Look for array pattern first
        int startIndex = text.indexOf('[');
        int endIndex = text.lastIndexOf(']') + 1;

        // If found an array, return it
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return text.substring(startIndex, endIndex);
        }

        // If no array found, look for object pattern
        startIndex = text.indexOf('{');
        endIndex = text.lastIndexOf('}') + 1;

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return text.substring(startIndex, endIndex);
        }

        // If no JSON found, return empty array
        return "[]";
    }
}