CREATE TABLE IF NOT EXISTS UserAuthentication (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER',
    last_login TIMESTAMP,
    account_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    height DECIMAL(5,2),
    weight DECIMAL(5,2),
    bmi DECIMAL(5,2),
    phone_number VARCHAR(20),
    address TEXT,
    profile_picture_url VARCHAR(255),
    blood_type ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'),
    medical_conditions TEXT, -- asthma, heart, knee, discomfort in any body part
    allergies TEXT,
    medications TEXT,
    dietary_preference ENUM('Vegetarian', 'Non-Vegetarian', 'Vegan'),
    age INT,
    FOREIGN KEY (user_id) REFERENCES UserAuthentication(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS FitnessGoals (
    goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    goal_type ENUM('Weight Loss', 'Muscle Gain', 'Maintain Weight', 'Endurance',
                       'Flexibility', 'Strength', 'Speed', 'Balance', 'Cardio Fitness',
                       'Sports Performance', 'Mental Wellness', 'Nutrition', 'Habit Formation') DEFAULT 'Weight Loss',
    target_weight DECIMAL(5,2),
    target_body_fat DECIMAL(4,2),
    start_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    target_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HealthLogs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    log_type ENUM('Meal', 'Exercise', 'Measurement'),
    description TEXT,
    calories INT DEFAULT 0,
    protein DECIMAL(5,2) DEFAULT 0,
    carbohydrates DECIMAL(5,2) DEFAULT 0,
    fats DECIMAL(5,2) DEFAULT 0,
    duration_minutes INT DEFAULT 0,
    intensity ENUM('Low', 'Medium', 'High'),
    weight DECIMAL(5,2),
    body_fat DECIMAL(4,2),
    blood_pressure_systolic INT,
    blood_pressure_diastolic INT,
    heart_rate INT,
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sleep INT,
    water_intake INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS WorkoutRecommendations (
    workout_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    goal_id INT,
    workout_name VARCHAR(100),
    workout_description TEXT,
    exercise_type ENUM('Cardio', 'Strength', 'Flexibility', 'Balance', 'HIIT', 'Low Impact'),
    duration_minutes INT,
    calories_burned INT,
    difficulty_level ENUM('Beginner', 'Intermediate', 'Advanced'),
    frequency_per_week INT,
    equipment_needed TEXT,
    video_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (goal_id) REFERENCES FitnessGoals(goal_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS DietRecommendations (
    diet_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    goal_id INT,
    diet_name VARCHAR(100),
    diet_description TEXT,
    calories_per_day INT,
    protein_percentage DECIMAL(4,2),
    carbs_percentage DECIMAL(4,2),
    fat_percentage DECIMAL(4,2),
    meal_type ENUM('breakfast', 'lunch', 'dinner'),
    hydration_recommendation TEXT,
    foods_to_include TEXT,
    foods_to_avoid TEXT,
    supplements_recommended TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (goal_id) REFERENCES FitnessGoals(goal_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Consultants (
    Consultants_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    designation VARCHAR(50),
    phone_no VARCHAR(50),
    email VARCHAR(100),
    notes TEXT

);

--CREATE TABLE IF NOT EXISTS FitnessGoals (
--    goal_id INT PRIMARY KEY AUTO_INCREMENT,
--    user_id INT NOT NULL,
--    goal_type ENUM('Weight Loss', 'Muscle Gain', 'Maintain Weight', 'Endurance') NOT NULL,
--    target_weight DECIMAL(5,2),
--    target_body_fat DECIMAL(4,2),
--    target_date DATE NOT NULL,
--    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
--    status ENUM('Active', 'Completed', 'Abandoned') NOT NULL DEFAULT 'Active',
--    notes TEXT,
--    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
--);