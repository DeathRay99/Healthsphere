                   -- Insert dummy data into UserAuthentication table
                   INSERT INTO UserAuthentication (username, email, password_hash, role, last_login, account_created, is_active) VALUES
                   ('john_doe', 'john@example.com', 'hashed_password_123', 'USER', NULL, NOW(), TRUE),
                   ('jane_doe', 'jane@example.com', 'hashed_password_456', 'USER', NULL, NOW(), TRUE),
                   ('admin_user', 'admin@example.com', 'hashed_password_admin', 'ADMIN', NULL, NOW(), TRUE);

                   -- Insert dummy data into Users table
                   INSERT INTO Users (user_id, first_name, last_name, date_of_birth, gender, height, weight, bmi, phone_number, address, profile_picture_url, blood_type, medical_conditions, allergies, medications, dietary_preference, age) VALUES
                   (1, 'John', 'Doe', '1990-01-01', 'Male', 175, 70, 22.86, '1234567890', '123 Main St', 'http://example.com/john.jpg', 'O+', 'None', 'None', 'None', 'Non-Vegetarian', 35),
                   (2, 'Jane', 'Doe', '1992-02-02', 'Female', 165, 60, 22.04, '0987654321', '456 Oak St', 'http://example.com/jane.jpg', 'A+', 'Asthma', 'Peanuts', 'Inhaler', 'Vegetarian', 33);

                   -- Insert dummy data into FitnessGoals table
                   INSERT INTO FitnessGoals (user_id, goal_type, target_weight, target_body_fat, target_date) VALUES
                   (1, 'Weight Loss', 65, 15, '2025-12-31'),
                   (2, 'Muscle Gain', 62, 18, '2025-06-30');

                   -- Insert dummy data into HealthLogs table
                   INSERT INTO HealthLogs (user_id, log_type, description, calories, protein, carbohydrates, fats, duration_minutes, intensity, weight, body_fat, blood_pressure_systolic, blood_pressure_diastolic, heart_rate, log_date, sleep, water_intake) VALUES
                   (1, 'Meal', 'Breakfast - Oatmeal', 300, 10, 50, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NOW(), 8, 2000),
                   (1, 'Exercise', 'Morning Run', 400, 0, 0, 0, 30, 'Medium', NULL, NULL, NULL, NULL, NULL, NOW(), 0, 0),
                   (2, 'Measurement', 'Monthly Checkup', 0, 0, 0, 0, 0, NULL, 60.5, 18.5, 120, 80, 70, NOW(), 0, 0);

                   -- Insert dummy data into WorkoutRecommendations table
                   INSERT INTO WorkoutRecommendations (user_id, goal_id, workout_name, workout_description, exercise_type, duration_minutes, calories_burned, difficulty_level, frequency_per_week, equipment_needed, video_url) VALUES
                   (1, 1, 'Morning Jog', 'A light morning jog to start the day', 'Cardio', 30, 200, 'Beginner', 5, 'Running shoes', 'http://example.com/jog.mp4'),
                   (2, 2, 'Strength Training', 'Full body strength training', 'Strength', 45, 300, 'Intermediate', 3, 'Dumbbells', 'http://example.com/strength.mp4');

                   -- Insert dummy data into DietRecommendations table
                   INSERT INTO DietRecommendations (user_id, goal_id, diet_name, diet_description, calories_per_day, protein_percentage, carbs_percentage, fat_percentage, meal_frequency, hydration_recommendation, foods_to_include, foods_to_avoid, supplements_recommended) VALUES
                   (1, 1, 'Low Carb Diet', 'A low carbohydrate diet to promote weight loss', 1500, 30, 40, 30, 3, 'Drink 2 liters of water daily', 'Chicken, Vegetables, Nuts', 'Sugar, Bread, Pasta', 'Multivitamin, Omega-3'),
                   (2, 2, 'High Protein Diet', 'A high protein diet to promote muscle gain', 2000, 40, 30, 30, 4, 'Drink 3 liters of water daily', 'Fish, Eggs, Quinoa', 'Soda, Candy', 'Protein Powder, Creatine');

                   -- Insert dummy data into Consultants table
                   INSERT INTO Consultants (first_name, last_name, designation, phone_no, email, notes) VALUES
                   ('Dr.', 'Smith', 'Nutritionist', '5551234567', 'dr.smith@example.com', 'Specializes in weight loss and muscle gain'),
                   ('Dr.', 'Jones', 'Personal Trainer', '5559876543', 'dr.jones@example.com', 'Certified personal trainer with 10 years of experience');
