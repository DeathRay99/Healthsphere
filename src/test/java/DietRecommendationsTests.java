package com.app.HealthSphere.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DietRecommendationsTest {

    @Test
    void testDefaultConstructor() {
        // Test the default constructor
        DietRecommendations diet = new DietRecommendations();
        assertNotNull(diet, "Default constructor should create a non-null object.");
    }

    @Test
    void testParameterizedConstructor() {
        // Set up test data
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DietRecommendations diet = new DietRecommendations(
                1, 1001, 201, "Weight Loss Diet", "High protein, low carb",
                2000, new BigDecimal("0.25"), new BigDecimal("0.50"),
                new BigDecimal("0.25"), "Breakfast", "Drink 3 liters of water daily",
                "Chicken, Vegetables", "Sugars, Fried Foods", "Multivitamins", now, now
        );

        // Verify the constructor assigns values correctly
        assertEquals(1, diet.getDietId());
        assertEquals(1001, diet.getUserId());
        assertEquals(201, diet.getGoalId());
        assertEquals("Weight Loss Diet", diet.getDietName());
        assertEquals("High protein, low carb", diet.getDietDescription());
        assertEquals(2000, diet.getCaloriesPerDay());
        assertEquals(new BigDecimal("0.25"), diet.getProteinPercentage());
        assertEquals(new BigDecimal("0.50"), diet.getCarbsPercentage());
        assertEquals(new BigDecimal("0.25"), diet.getFatPercentage());
        assertEquals("Breakfast", diet.getMealType());
        assertEquals("Drink 3 liters of water daily", diet.getHydrationRecommendation());
        assertEquals("Chicken, Vegetables", diet.getFoodsToInclude());
        assertEquals("Sugars, Fried Foods", diet.getFoodsToAvoid());
        assertEquals("Multivitamins", diet.getSupplementsRecommended());
        assertEquals(now, diet.getCreatedAt());
        assertEquals(now, diet.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        // Create an instance of DietRecommendations
        DietRecommendations diet = new DietRecommendations();

        // Set values using setters
        diet.setDietId(2);
        diet.setUserId(1002);
        diet.setGoalId(202);
        diet.setDietName("Keto Diet");
        diet.setDietDescription("Low carb, high fat");
        diet.setCaloriesPerDay(1800);
        diet.setProteinPercentage(new BigDecimal("0.30"));
        diet.setCarbsPercentage(new BigDecimal("0.10"));
        diet.setFatPercentage(new BigDecimal("0.60"));
        diet.setMealType("Lunch");
        diet.setHydrationRecommendation("Drink 2 liters of water daily");
        diet.setFoodsToInclude("Avocado, Eggs");
        diet.setFoodsToAvoid("Bread, Sugar");
        diet.setSupplementsRecommended("Omega-3");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        diet.setCreatedAt(now);
        diet.setUpdatedAt(now);

        // Verify values using getters
        assertEquals(2, diet.getDietId());
        assertEquals(1002, diet.getUserId());
        assertEquals(202, diet.getGoalId());
        assertEquals("Keto Diet", diet.getDietName());
        assertEquals("Low carb, high fat", diet.getDietDescription());
        assertEquals(1800, diet.getCaloriesPerDay());
        assertEquals(new BigDecimal("0.30"), diet.getProteinPercentage());
        assertEquals(new BigDecimal("0.10"), diet.getCarbsPercentage());
        assertEquals(new BigDecimal("0.60"), diet.getFatPercentage());
        assertEquals("Lunch", diet.getMealType());
        assertEquals("Drink 2 liters of water daily", diet.getHydrationRecommendation());
        assertEquals("Avocado, Eggs", diet.getFoodsToInclude());
        assertEquals("Bread, Sugar", diet.getFoodsToAvoid());
        assertEquals("Omega-3", diet.getSupplementsRecommended());
        assertEquals(now, diet.getCreatedAt());
        assertEquals(now, diet.getUpdatedAt());
    }

    @Test
    void testBoundaryValues() {
        // Test boundary values for numerical and percentage fields
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DietRecommendations diet = new DietRecommendations(
                Integer.MAX_VALUE, Integer.MIN_VALUE, 0, "", "",
                0, new BigDecimal("0.0"), new BigDecimal("0.0"),
                new BigDecimal("0.0"), null, null, null, null, null, now, now
        );

        // Assertions for boundary values
        assertEquals(Integer.MAX_VALUE, diet.getDietId());
        assertEquals(Integer.MIN_VALUE, diet.getUserId());
        assertEquals(0, diet.getGoalId());
        assertEquals("", diet.getDietName());
        assertEquals("", diet.getDietDescription());
        assertEquals(0, diet.getCaloriesPerDay());
        assertEquals(new BigDecimal("0.0"), diet.getProteinPercentage());
        assertEquals(new BigDecimal("0.0"), diet.getCarbsPercentage());
        assertEquals(new BigDecimal("0.0"), diet.getFatPercentage());
        assertNull(diet.getMealType());
        assertNull(diet.getHydrationRecommendation());
        assertNull(diet.getFoodsToInclude());
        assertNull(diet.getFoodsToAvoid());
        assertNull(diet.getSupplementsRecommended());
        assertEquals(now, diet.getCreatedAt());
        assertEquals(now, diet.getUpdatedAt());
    }

    @Test
    void testExceptionalCases() {
        DietRecommendations diet = new DietRecommendations();

        // Test invalid calories per day
        Exception invalidCaloriesException = assertThrows(IllegalArgumentException.class, () -> {
            diet.setCaloriesPerDay(-200);
        });
        assertEquals("Calories per day cannot be negative.", invalidCaloriesException.getMessage());

        // Test invalid protein percentage
        Exception invalidProteinException = assertThrows(IllegalArgumentException.class, () -> {
            diet.setProteinPercentage(new BigDecimal("1.5")); // Invalid: exceeds 1.0 (100%)
        });
        assertEquals("Protein percentage must be between 0 and 1.", invalidProteinException.getMessage());

        // Test invalid fat percentage
        Exception invalidFatException = assertThrows(IllegalArgumentException.class, () -> {
            diet.setFatPercentage(new BigDecimal("-0.1")); // Invalid: negative value
        });
        assertEquals("Fat percentage cannot be negative.", invalidFatException.getMessage());
    }
}
