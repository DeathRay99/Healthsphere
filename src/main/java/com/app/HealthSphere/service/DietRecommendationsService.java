package com.app.HealthSphere.service;

import com.app.HealthSphere.model.DietRecommendations;
import com.app.HealthSphere.repository.DietRecommendationsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DietRecommendationsService {

    private final DietRecommendationsRepository dietRecommendationsRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Autowired
    public DietRecommendationsService(
            DietRecommendationsRepository dietRecommendationsRepository,
            JdbcTemplate jdbcTemplate,
            ObjectMapper objectMapper,
            WebClient.Builder webClientBuilder,
            @Value("${gemini.api.url}") String geminiApiUrl) {
        this.dietRecommendationsRepository = dietRecommendationsRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(geminiApiUrl).build();
    }

    // Save diet recommendation
    public int saveDietRecommendation(DietRecommendations dietRecommendations) {
        return dietRecommendationsRepository.save(dietRecommendations);
    }

    // Find all diet recommendations
    public List<DietRecommendations> findAllDietRecommendations() {
        return dietRecommendationsRepository.findAll();
    }

    // Find diet recommendation by ID
    public List<DietRecommendations> findDietRecommendationById(int userId) {
        return dietRecommendationsRepository.findById(userId);
    }

    // Update diet recommendation
    public int updateDietRecommendation(DietRecommendations dietRecommendations) {
        return dietRecommendationsRepository.update(dietRecommendations);
    }

    // Delete diet recommendation by ID
    public int deleteDietRecommendationById(int dietId) {
        return dietRecommendationsRepository.deleteById(dietId);
    }

    // AI-powered diet recommendation generation methods


    public List<DietRecommendations> generateDietRecommendations(int userId, int goalId) {
        Map<String, Object> userProfile = fetchUserProfile(userId, goalId);
        String prompt = buildGeminiPrompt(userProfile);
        String aiResponse = callGeminiApi(prompt);

        // Parse the AI response and prepare the recommendations
        List<DietRecommendations> recommendations = parseAiResponseToMealList(aiResponse, userId, goalId);

        // Save each recommendation
        for (DietRecommendations recommendation : recommendations) {
            saveDietRecommendation(recommendation);
        }

        return recommendations;
    }

    private Map<String, Object> fetchUserProfile(int userId, int goalId) {
        String sql = """
            SELECT u.gender, u.age, u.height, u.weight,\s
                   u.bmi,
                   f.goal_type, f.target_weight, f.target_body_fat, f.target_date,\s
                   u.dietary_preference, u.allergies, u.medications, u.medical_conditions
            FROM Users u\s
            JOIN FitnessGoals f ON u.user_id = f.user_id\s
            WHERE u.user_id = ? AND f.goal_id = ?
       \s""";

        return jdbcTemplate.queryForMap(sql, userId, goalId);
    }

    private String buildGeminiPrompt(Map<String, Object> userProfile) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Generate personalized diet recommendations based on the following user profile:\n\n");

        promptBuilder.append("### User Profile\n");
        promptBuilder.append("- Gender: ").append(userProfile.get("gender")).append("\n");
        promptBuilder.append("- Age: ").append(userProfile.get("age")).append("\n");
        promptBuilder.append("- Height: ").append(userProfile.get("height")).append(" cm\n");
        promptBuilder.append("- Current Weight: ").append(userProfile.get("weight")).append(" kg\n");
        promptBuilder.append("- BMI: ").append(userProfile.get("bmi")).append("\n");
        promptBuilder.append("- Dietary Preference: ").append(userProfile.get("dietary_preference")).append("\n");
        promptBuilder.append("- Allergies: ").append(userProfile.get("allergies")).append("\n");
        promptBuilder.append("- Medications: ").append(userProfile.get("medications")).append("\n");
        promptBuilder.append("- Medical Conditions: ").append(userProfile.get("medical_conditions")).append("\n\n");

        promptBuilder.append("### Fitness Goals\n");
        promptBuilder.append("- Goal Type: ").append(userProfile.get("goal_type")).append("\n");
        promptBuilder.append("- Target Weight: ").append(userProfile.get("target_weight")).append(" kg\n");
        promptBuilder.append("- Target Body Fat: ").append(userProfile.get("target_body_fat")).append("%\n");
        promptBuilder.append("- Target Date: ").append(userProfile.get("target_date")).append("\n\n");

        promptBuilder.append("### Recommendation Requirements\n");
        promptBuilder.append("Create three meal recommendations in a JSON array - one for breakfast, one for lunch, and one for dinner. ");
        promptBuilder.append("Provide a structured response in JSON format with an array containing three meal objects. ");
        promptBuilder.append("Each meal object should have these exact keys: ");
        promptBuilder.append("diet_name (string), diet_description (string), meal_type (string - must be exactly one of: 'breakfast', 'lunch', 'dinner'), ");
        promptBuilder.append("calories_per_day (integer), protein_percentage (number), carbs_percentage (number), fat_percentage (number), ");
        promptBuilder.append("hydration_recommendation (string), foods_to_include (string), foods_to_avoid (string), ");
        promptBuilder.append("supplements_recommended (string)\n\n");

        promptBuilder.append("The response must be a valid JSON array with three objects, enclosed in square brackets []. ");
        promptBuilder.append("For percentages, use decimal numbers that add up to 100 for each meal (example: protein_percentage: 25.5). ");
        promptBuilder.append("Ensure you include exactly one meal for each meal_type ('breakfast', 'lunch', 'dinner'). ");
        promptBuilder.append("For example: [{\"diet_name\": \"Energizing Breakfast\", \"meal_type\": \"breakfast\", ...}, ");
        promptBuilder.append("{\"diet_name\": \"Balanced Lunch\", \"meal_type\": \"lunch\", ...}, ");
        promptBuilder.append("{\"diet_name\": \"Light Dinner\", \"meal_type\": \"dinner\", ...}]");

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

    /**
     * Parse the AI response into a list of meal recommendations
     */
    private List<DietRecommendations> parseAiResponseToMealList(String aiResponse, int userId, int goalId) {
        List<DietRecommendations> mealRecommendations = new ArrayList<>();

        try {
            // Try to extract JSON array from the response
            String jsonContent = extractJsonArrayFromText(aiResponse);
            JsonNode arrayNode = objectMapper.readTree(jsonContent);

            // Check if we have a valid array
            if (arrayNode.isArray()) {
                // Process each meal in the array
                for (JsonNode mealNode : arrayNode) {
                    DietRecommendations meal = createMealFromJsonNode(mealNode, userId, goalId);
                    mealRecommendations.add(meal);
                }
            } else {
                // If not an array, try to parse as a single object
                DietRecommendations meal = createMealFromJsonNode(arrayNode, userId, goalId);
                mealRecommendations.add(meal);
            }
        } catch (Exception e) {
            // If parsing fails completely, create fallback meals
            mealRecommendations.add(createFallbackMeal(userId, goalId, "breakfast"));
            mealRecommendations.add(createFallbackMeal(userId, goalId, "lunch"));
            mealRecommendations.add(createFallbackMeal(userId, goalId, "dinner"));
        }

        // Ensure we have exactly one of each meal type
        ensureAllMealTypes(mealRecommendations, userId, goalId);

        return mealRecommendations;
    }

    /**
     * Create a meal recommendation from a JSON node
     */
    private DietRecommendations createMealFromJsonNode(JsonNode jsonNode, int userId, int goalId) {
        DietRecommendations recommendation = new DietRecommendations();
        recommendation.setUserId(userId);
        recommendation.setGoalId(goalId);

        // Set default values first
        recommendation.setDietName("");
        recommendation.setDietDescription("");
        recommendation.setMealType("");
        recommendation.setCaloriesPerDay(0);
        recommendation.setProteinPercentage(BigDecimal.ZERO);
        recommendation.setCarbsPercentage(BigDecimal.ZERO);
        recommendation.setFatPercentage(BigDecimal.ZERO);
        recommendation.setHydrationRecommendation("");
        recommendation.setFoodsToInclude("");
        recommendation.setFoodsToAvoid("");
        recommendation.setSupplementsRecommended("");

        // Override with actual values if they exist
        if (jsonNode.has("diet_name")) {
            recommendation.setDietName(jsonNode.get("diet_name").asText());
        }
        if (jsonNode.has("diet_description")) {
            recommendation.setDietDescription(jsonNode.get("diet_description").asText());
        }
        if (jsonNode.has("meal_type")) {
            recommendation.setMealType(jsonNode.get("meal_type").asText().toLowerCase());
        }
        if (jsonNode.has("calories_per_day")) {
            try {
                recommendation.setCaloriesPerDay(jsonNode.get("calories_per_day").asInt());
            } catch (Exception e) {
                String value = jsonNode.get("calories_per_day").asText();
                recommendation.setCaloriesPerDay(Integer.parseInt(value.replaceAll("[^0-9]", "")));
            }
        }
        if (jsonNode.has("protein_percentage")) {
            try {
                recommendation.setProteinPercentage(new BigDecimal(jsonNode.get("protein_percentage").asText()));
            } catch (Exception e) {
                String value = jsonNode.get("protein_percentage").asText().replaceAll("[^0-9.]", "");
                if (!value.isEmpty()) {
                    recommendation.setProteinPercentage(new BigDecimal(value));
                }
            }
        }
        if (jsonNode.has("carbs_percentage")) {
            try {
                recommendation.setCarbsPercentage(new BigDecimal(jsonNode.get("carbs_percentage").asText()));
            } catch (Exception e) {
                String value = jsonNode.get("carbs_percentage").asText().replaceAll("[^0-9.]", "");
                if (!value.isEmpty()) {
                    recommendation.setCarbsPercentage(new BigDecimal(value));
                }
            }
        }
        if (jsonNode.has("fat_percentage")) {
            try {
                recommendation.setFatPercentage(new BigDecimal(jsonNode.get("fat_percentage").asText()));
            } catch (Exception e) {
                String value = jsonNode.get("fat_percentage").asText().replaceAll("[^0-9.]", "");
                if (!value.isEmpty()) {
                    recommendation.setFatPercentage(new BigDecimal(value));
                }
            }
        }
        if (jsonNode.has("hydration_recommendation")) {
            recommendation.setHydrationRecommendation(jsonNode.get("hydration_recommendation").asText());
        }
        if (jsonNode.has("foods_to_include")) {
            recommendation.setFoodsToInclude(jsonNode.get("foods_to_include").asText());
        }
        if (jsonNode.has("foods_to_avoid")) {
            recommendation.setFoodsToAvoid(jsonNode.get("foods_to_avoid").asText());
        }
        if (jsonNode.has("supplements_recommended")) {
            recommendation.setSupplementsRecommended(jsonNode.get("supplements_recommended").asText());
        }

        // Set timestamps
        Timestamp now = Timestamp.from(Instant.now());
        recommendation.setCreatedAt(now);
        recommendation.setUpdatedAt(now);

        return recommendation;
    }

    /**
     * Create a fallback meal recommendation
     */
    private DietRecommendations createFallbackMeal(int userId, int goalId, String mealType) {
        DietRecommendations fallbackMeal = new DietRecommendations();
        fallbackMeal.setUserId(userId);
        fallbackMeal.setGoalId(goalId);
        fallbackMeal.setMealType(mealType);

        Timestamp now = Timestamp.from(Instant.now());
        fallbackMeal.setCreatedAt(now);
        fallbackMeal.setUpdatedAt(now);

        // Set default values based on meal type
        switch (mealType) {
            case "breakfast":
                fallbackMeal.setDietName("Basic Breakfast Plan");
                fallbackMeal.setDietDescription("A nutritious breakfast to start your day.");
                fallbackMeal.setCaloriesPerDay(500);
                fallbackMeal.setProteinPercentage(new BigDecimal("20.0"));
                fallbackMeal.setCarbsPercentage(new BigDecimal("60.0"));
                fallbackMeal.setFatPercentage(new BigDecimal("20.0"));
                fallbackMeal.setFoodsToInclude("Oatmeal, eggs, whole grain toast, fruits, yogurt");
                fallbackMeal.setFoodsToAvoid("Sugary cereals, pastries, white bread");
                break;

            case "lunch":
                fallbackMeal.setDietName("Standard Lunch Plan");
                fallbackMeal.setDietDescription("A balanced lunch to maintain energy throughout the day.");
                fallbackMeal.setCaloriesPerDay(700);
                fallbackMeal.setProteinPercentage(new BigDecimal("30.0"));
                fallbackMeal.setCarbsPercentage(new BigDecimal("45.0"));
                fallbackMeal.setFatPercentage(new BigDecimal("25.0"));
                fallbackMeal.setFoodsToInclude("Lean proteins, whole grains, vegetables, legumes");
                fallbackMeal.setFoodsToAvoid("Fast food, processed meats, sugary drinks");
                break;

            case "dinner":
                fallbackMeal.setDietName("Light Dinner Plan");
                fallbackMeal.setDietDescription("A lighter evening meal for better sleep and digestion.");
                fallbackMeal.setCaloriesPerDay(600);
                fallbackMeal.setProteinPercentage(new BigDecimal("25.0"));
                fallbackMeal.setCarbsPercentage(new BigDecimal("40.0"));
                fallbackMeal.setFatPercentage(new BigDecimal("35.0"));
                fallbackMeal.setFoodsToInclude("Fish, chicken, vegetables, soups, salads");
                fallbackMeal.setFoodsToAvoid("Heavy carbs, large portions, alcohol");
                break;

            default:
                fallbackMeal.setDietName("Generic Meal Plan");
                fallbackMeal.setDietDescription("A balanced nutrition plan.");
                fallbackMeal.setCaloriesPerDay(600);
                fallbackMeal.setProteinPercentage(new BigDecimal("25.0"));
                fallbackMeal.setCarbsPercentage(new BigDecimal("50.0"));
                fallbackMeal.setFatPercentage(new BigDecimal("25.0"));
                fallbackMeal.setFoodsToInclude("Lean proteins, whole grains, fruits, vegetables");
                fallbackMeal.setFoodsToAvoid("Processed foods, excessive sugar, alcohol");
        }

        // Common fields across all meal types
        fallbackMeal.setHydrationRecommendation("Drink 8 glasses of water daily");
        fallbackMeal.setSupplementsRecommended("Multivitamin");

        return fallbackMeal;
    }

    /**
     * Ensure we have exactly one of each meal type (breakfast, lunch, dinner)
     */
    private void ensureAllMealTypes(List<DietRecommendations> recommendations, int userId, int goalId) {
        boolean hasBreakfast = false;
        boolean hasLunch = false;
        boolean hasDinner = false;

        // Check what meal types we already have
        for (DietRecommendations meal : recommendations) {
            String mealType = meal.getMealType().toLowerCase();
            if (mealType.equals("breakfast")) {
                hasBreakfast = true;
            } else if (mealType.equals("lunch")) {
                hasLunch = true;
            } else if (mealType.equals("dinner")) {
                hasDinner = true;
            }
        }

        // Add any missing meal types
        if (!hasBreakfast) {
            recommendations.add(createFallbackMeal(userId, goalId, "breakfast"));
        }
        if (!hasLunch) {
            recommendations.add(createFallbackMeal(userId, goalId, "lunch"));
        }
        if (!hasDinner) {
            recommendations.add(createFallbackMeal(userId, goalId, "dinner"));
        }
    }

    /**
     * Helper method to extract JSON array from a text that might contain additional content
     */
    private String extractJsonArrayFromText(String text) {
        // First try to find array pattern with square brackets
        int startIndex = text.indexOf('[');
        int endIndex = text.lastIndexOf(']') + 1;

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return text.substring(startIndex, endIndex);
        }

        // If no array found, try to find a JSON object
        startIndex = text.indexOf('{');
        endIndex = text.lastIndexOf('}') + 1;

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return text.substring(startIndex, endIndex);
        }

        // If no JSON found, return the original text
        return text;
    }

    /**
     * For backward compatibility
     */
    public DietRecommendations generateDietRecommendation(int userId, int goalId) {
        // Call the new method that returns a list
        List<DietRecommendations> recommendations = generateDietRecommendations(userId, goalId);

        // Return the first recommendation (likely breakfast)
        return recommendations.isEmpty() ?
                createFallbackMeal(userId, goalId, "breakfast") :
                recommendations.get(0);
    }

    // Find diet recommendations by userId and goalId

    public List<DietRecommendations> findDietsByUserAndGoalId(int userId, int goalId) {
        return dietRecommendationsRepository.findByUserAndGoalId(userId,goalId);
    }
}