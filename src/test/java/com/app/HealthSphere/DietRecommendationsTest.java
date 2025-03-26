package com.app.HealthSphere;
import com.app.HealthSphere.model.DietRecommendations;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DietRecommendationsTest {

    @Test
    public void testConstructorInitialization() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DietRecommendations diet = new DietRecommendations(1, 101, 201, "Keto Diet", "High protein low carb",
                2000, BigDecimal.valueOf(40.0), BigDecimal.valueOf(30.0), BigDecimal.valueOf(30.0),
                "Lunch", "2 Liters of Water", "Eggs, Chicken, Vegetables", "Sugar, Bread",
                "Multivitamins", now, now);

        assertThat(diet.getDietId()).isEqualTo(1);
        assertThat(diet.getUserId()).isEqualTo(101);
        assertThat(diet.getGoalId()).isEqualTo(201);
        assertThat(diet.getDietName()).isEqualTo("Keto Diet");
        assertThat(diet.getCaloriesPerDay()).isEqualTo(2000);
        assertThat(diet.getProteinPercentage()).isEqualTo(BigDecimal.valueOf(40.0));
        assertThat(diet.getCarbsPercentage()).isEqualTo(BigDecimal.valueOf(30.0));
        assertThat(diet.getFatPercentage()).isEqualTo(BigDecimal.valueOf(30.0));
        assertThat(diet.getMealType()).isEqualTo("Lunch");
    }

    @Test
    public void testSettersAndGetters() {
        DietRecommendations diet = new DietRecommendations();
        diet.setDietId(2);
        diet.setUserId(202);
        diet.setGoalId(302);
        diet.setDietName("Vegan Diet");
        diet.setCaloriesPerDay(1800);
        diet.setProteinPercentage(BigDecimal.valueOf(20.5));
        diet.setCarbsPercentage(BigDecimal.valueOf(50.0));
        diet.setFatPercentage(BigDecimal.valueOf(29.5));
        diet.setMealType("Dinner");
        diet.setHydrationRecommendation("3 Liters of Water");
        diet.setFoodsToInclude("Fruits, Vegetables, Nuts");
        diet.setFoodsToAvoid("Meat, Dairy");
        diet.setSupplementsRecommended("B12, Iron");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        diet.setCreatedAt(now);
        diet.setUpdatedAt(now);

        assertThat(diet.getDietId()).isEqualTo(2);
        assertThat(diet.getUserId()).isEqualTo(202);
        assertThat(diet.getGoalId()).isEqualTo(302);
        assertThat(diet.getDietName()).isEqualTo("Vegan Diet");
        assertThat(diet.getCaloriesPerDay()).isEqualTo(1800);
        assertThat(diet.getProteinPercentage()).isEqualTo(BigDecimal.valueOf(20.5));
        assertThat(diet.getCarbsPercentage()).isEqualTo(BigDecimal.valueOf(50.0));
        assertThat(diet.getFatPercentage()).isEqualTo(BigDecimal.valueOf(29.5));
        assertThat(diet.getMealType()).isEqualTo("Dinner");
    }

    @Test
    public void testEdgeCase_ZeroCalories() {
        DietRecommendations diet = new DietRecommendations();
        diet.setCaloriesPerDay(0);
        assertThat(diet.getCaloriesPerDay()).isEqualTo(0);
    }

    @Test
    public void testNegativeProteinPercentage() {
        DietRecommendations diet = new DietRecommendations();
        diet.setProteinPercentage(BigDecimal.valueOf(-10.0));
        assertThat(diet.getProteinPercentage()).isEqualTo(BigDecimal.valueOf(-10.0));
    }

    @Test
    public void testNullValues() {
        DietRecommendations diet = new DietRecommendations();
        diet.setDietName(null);
        diet.setHydrationRecommendation(null);
        assertThat(diet.getDietName()).isNull();
        assertThat(diet.getHydrationRecommendation()).isNull();
    }
}
