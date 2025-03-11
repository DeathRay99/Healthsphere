-- Insert data into UserAuthentication table
INSERT INTO UserAuthentication (username, email, password_hash, last_login, is_active)
VALUES
('john_doe', 'john@example.com', 'hashed_password_1', '2025-03-10 10:20:30', TRUE),
('jane_smith', 'jane@example.com', 'hashed_password_2', '2025-03-11 11:30:40', TRUE),
('alex_jones', 'alex@example.com', 'hashed_password_3', '2025-03-12 12:40:50', TRUE);

-- Insert data into Users table
INSERT INTO Users (user_id, first_name, last_name, date_of_birth, gender, height, weight, bmi, phone_number, address, profile_picture_url, blood_type, medical_conditions, allergies, workout_intensity, dietary_preference)
VALUES
(1, 'John', 'Doe', '1990-01-01', 'Male', 180.50, 75.00, 23.10, '1234567890', '123 Main St', 'https://example.com/john.jpg', 'A+', 'asthma', 'peanuts', 'light', 'Non-Vegetarian'),
(2, 'Jane', 'Smith', '1985-05-15', 'Female', 165.75, 60.00, 21.90, '0987654321', '456 Park Ave', 'https://example.com/jane.jpg', 'B-', 'heart', 'seafood', 'heavy', 'Vegetarian'),
(3, 'Alex', 'Jones', '1992-08-25', 'Other', 170.00, 68.00, 23.50, '1231231234', '789 Market St', 'https://example.com/alex.jpg', 'O+', 'knee', 'dust', 'intermediate', 'Vegan');

-- Insert data into FitnessGoals table
INSERT INTO FitnessGoals (user_id, goal_type, target_weight, target_body_fat, target_date)
VALUES
(1, 'Weight Loss', 70.00, 20.00, '2025-12-31'),
(2, 'Muscle Gain', 65.00, 18.00, '2025-11-30'),
(3, 'Maintain Weight', 68.00, 21.00, '2025-10-31');

-- Insert data into HealthLogs table
INSERT INTO HealthLogs (user_id, log_type, description, calories, protein, carbohydrates, fats, duration_minutes, intensity, weight, body_fat, blood_pressure_systolic, blood_pressure_diastolic, heart_rate, log_date)
VALUES
(1, 'Meal', 'Breakfast', 300, 10.00, 40.00, 10.00, 0, 'Low', NULL, NULL, NULL, NULL, NULL, '2025-03-10 08:00:00'),
(2, 'Exercise', 'Running', 0, 0.00, 0.00, 0.00, 60, 'High', 60.00, 18.00, NULL, NULL, NULL, '2025-03-11 07:00:00'),
(3, 'Measurement', 'Weekly check-in', 0, 0.00, 0.00, 0.00, 0, 'Medium', 68.00, 23.50, 120, 80, 70, '2025-03-12 09:00:00');

-- Insert data into WorkoutRecommendations table
INSERT INTO WorkoutRecommendations (user_id, goal_id, workout_name, workout_description, exercise_type, duration_minutes, calories_burned, difficulty_level, frequency_per_week, equipment_needed, video_url)
VALUES
(1, 1, 'Morning Jog', 'Jogging for 30 minutes', 'Cardio', 30, 200, 'Beginner', 5, 'None', 'https://example.com/morning_jog_video'),
(2, 2, 'Strength Training', 'Weight lifting session', 'Strength', 45, 300, 'Intermediate', 4, 'Dumbbells', 'https://example.com/strength_training_video'),
(3, 3, 'Yoga Session', 'Yoga for flexibility', 'Flexibility', 60, 150, 'Advanced', 3, 'Yoga Mat', 'https://example.com/yoga_video');

-- Insert data into DietRecommendations table
INSERT INTO DietRecommendations (user_id, goal_id, diet_name, diet_description, calories_per_day, protein_percentage, carbs_percentage, fat_percentage, meal_frequency, hydration_recommendation, foods_to_include, foods_to_avoid, supplements_recommended)
VALUES
(1, 1, 'Low Carb Diet', 'A diet with low carbohydrates', 1800, 30.00, 40.00, 30.00, 4, 'Drink 2 liters of water daily', 'Lean meats, vegetables', 'Sugary foods, pasta', 'Vitamin D, Omega-3'),
(2, 2, 'High Protein Diet', 'A diet with high protein intake', 2200, 40.00, 30.00, 30.00, 5, 'Drink 2.5 liters of water daily', 'Chicken breast, beans', 'Fried foods, sugary drinks', 'Protein powder'),
(3, 3, 'Balanced Diet', 'A diet with balanced nutrients', 2000, 25.00, 50.00, 25.00, 3, 'Drink 3 liters of water daily', 'Fruits, whole grains', 'Processed foods, excess salt', 'Multivitamins');

-- Insert data into Consultants table
INSERT INTO Consultants (first_name, last_name, designation, phone_no, email, notes)
VALUES
('Dr. Emma', 'Stone', 'Nutritionist', '9876543210', 'emma.stone@example.com', 'Specializes in diet plans for weight loss'),
('Dr. Liam', 'Smith', 'Physiotherapist', '8765432109', 'liam.smith@example.com', 'Focuses on rehabilitation exercises'),
('Dr. Ava', 'Johnson', 'Personal Trainer', '7654321098', 'ava.johnson@example.com', 'Expert in strength training and endurance workouts');
