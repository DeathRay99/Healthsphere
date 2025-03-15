package com.app.HealthSphere;

import com.app.HealthSphere.model.DietRecommendations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

class DietRecommendationsTests {

    @Test
    void testDietRecommendationsCreation() {
        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp updatedAt = Timestamp.from(Instant.now());

        DietRecommendations diet = new DietRecommendations(1, "Keto Diet", "Low carbs, high fat diet",
                2000, BigDecimal.valueOf(30), BigDecimal.valueOf(50),
                BigDecimal.valueOf(20), 3, "Drink 2 liters of water daily",
                "Meat, Cheese", "Sugar, Bread", "Omega-3 Supplements", createdAt, updatedAt);

        assertNotNull(diet);
        assertEquals(1, diet.getDietId());
        assertEquals("Keto Diet", diet.getDietName());
        assertEquals(2000, diet.getCaloriesPerDay());
        assertEquals(BigDecimal.valueOf(30), diet.getProteinPercentage());
        assertEquals(BigDecimal.valueOf(50), diet.getCarbsPercentage());
        assertEquals(BigDecimal.valueOf(20), diet.getFatPercentage());
        assertEquals(3, diet.getMealFrequency());
        assertEquals("Drink 2 liters of water daily", diet.getHydrationRecommendation());
        assertEquals("Meat, Cheese", diet.getFoodsToInclude());
        assertEquals("Sugar, Bread", diet.getFoodsToAvoid());
        assertEquals("Omega-3 Supplements", diet.getSupplementsRecommended());
        assertEquals(createdAt, diet.getCreatedAt());
        assertEquals(updatedAt, diet.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        DietRecommendations diet = new DietRecommendations();

        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp updatedAt = Timestamp.from(Instant.now());

        diet.setDietId(1);
        diet.setDietName("Paleo Diet");
        diet.setDietDescription("Focus on unprocessed foods");
        diet.setCaloriesPerDay(2500);
        diet.setProteinPercentage(BigDecimal.valueOf(35));
        diet.setCarbsPercentage(BigDecimal.valueOf(40));
        diet.setFatPercentage(BigDecimal.valueOf(25));
        diet.setMealFrequency(4);
        diet.setHydrationRecommendation("Drink at least 3 liters daily");
        diet.setFoodsToInclude("Fruits, Vegetables");
        diet.setFoodsToAvoid("Dairy, Sugar");
        diet.setSupplementsRecommended("Vitamin D");
        diet.setCreatedAt(createdAt);
        diet.setUpdatedAt(updatedAt);

        assertEquals(1, diet.getDietId());
        assertEquals("Paleo Diet", diet.getDietName());
        assertEquals("Focus on unprocessed foods", diet.getDietDescription());
        assertEquals(2500, diet.getCaloriesPerDay());
        assertEquals(BigDecimal.valueOf(35), diet.getProteinPercentage());
        assertEquals(BigDecimal.valueOf(40), diet.getCarbsPercentage());
        assertEquals(BigDecimal.valueOf(25), diet.getFatPercentage());
        assertEquals(4, diet.getMealFrequency());
        assertEquals("Drink at least 3 liters daily", diet.getHydrationRecommendation());
        assertEquals("Fruits, Vegetables", diet.getFoodsToInclude());
        assertEquals("Dairy, Sugar", diet.getFoodsToAvoid());
        assertEquals("Vitamin D", diet.getSupplementsRecommended());
        assertEquals(createdAt, diet.getCreatedAt());
        assertEquals(updatedAt, diet.getUpdatedAt());
    }

    @Test
    void testInvalidDietId() {
        DietRecommendations diet = new DietRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setDietId(-1));
        assertEquals("Diet ID must be a positive number.", exception.getMessage());
    }

    @Test
    void testInvalidDietName() {
        DietRecommendations diet = new DietRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setDietName(""));
        assertEquals("Diet Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testInvalidCaloriesPerDay() {
        DietRecommendations diet = new DietRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setCaloriesPerDay(-500));
        assertEquals("Calories per day cannot be negative.", exception.getMessage());
    }

    @Test
    void testInvalidProteinPercentage() {
        DietRecommendations diet = new DietRecommendations();

        Exception lowException = assertThrows(IllegalArgumentException.class, () -> diet.setProteinPercentage(BigDecimal.valueOf(-5)));
        assertEquals("Protein percentage must be between 0 and 100.", lowException.getMessage());

        Exception highException = assertThrows(IllegalArgumentException.class, () -> diet.setProteinPercentage(BigDecimal.valueOf(105)));
        assertEquals("Protein percentage must be between 0 and 100.", highException.getMessage());
    }

    @Test
    void testInvalidCarbsPercentage() {
        DietRecommendations diet = new DietRecommendations();

        Exception lowException = assertThrows(IllegalArgumentException.class, () -> diet.setCarbsPercentage(BigDecimal.valueOf(-10)));
        assertEquals("Carbohydrates percentage must be between 0 and 100.", lowException.getMessage());

        Exception highException = assertThrows(IllegalArgumentException.class, () -> diet.setCarbsPercentage(BigDecimal.valueOf(120)));
        assertEquals("Carbohydrates percentage must be between 0 and 100.", highException.getMessage());
    }

    @Test
    void testInvalidFatPercentage() {
        DietRecommendations diet = new DietRecommendations();

        Exception lowException = assertThrows(IllegalArgumentException.class, () -> diet.setFatPercentage(BigDecimal.valueOf(-8)));
        assertEquals("Fat percentage must be between 0 and 100.", lowException.getMessage());

        Exception highException = assertThrows(IllegalArgumentException.class, () -> diet.setFatPercentage(BigDecimal.valueOf(150)));
        assertEquals("Fat percentage must be between 0 and 100.", highException.getMessage());
    }

    @Test
    void testInvalidMealFrequency() {
        DietRecommendations diet = new DietRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setMealFrequency(-2));
        assertEquals("Meal frequency must be a positive integer.", exception.getMessage());
    }

    @Test
    void testInvalidCreatedAtTimestamp() {
        DietRecommendations diet = new DietRecommendations();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setCreatedAt(null));
        assertEquals("Creation timestamp cannot be null.", exception.getMessage());
    }

    @Test
    void testInvalidUpdatedAtTimestamp() {
        DietRecommendations diet = new DietRecommendations();
        Timestamp createdAt = Timestamp.from(Instant.now());
        Timestamp pastUpdatedAt = Timestamp.from(Instant.now().minusSeconds(3600)); // One hour earlier

        diet.setCreatedAt(createdAt);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> diet.setUpdatedAt(pastUpdatedAt));
        assertEquals("Updated timestamp cannot be earlier than created timestamp.", exception.getMessage());
    }
}
