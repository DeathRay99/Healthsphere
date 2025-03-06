CREATE TABLE IF NOT EXISTS UserAuthentication (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    last_login TIMESTAMP,
    account_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Recommendations (
    recommendation_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    type ENUM('Meal', 'Workout'),
    recommended_item VARCHAR(100),
    reason TEXT,
    user_feedback ENUM('Liked', 'Disliked', 'Neutral'),
    recommended_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES UserAuthentication(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    height DECIMAL(5,2),
    weight DECIMAL(5,2),
    phone_number VARCHAR(20),
    address TEXT,
    profile_picture_url VARCHAR(255),
    blood_type ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'),
    medical_conditions TEXT,
    allergies TEXT,
    medications TEXT,
    dietary_preference ENUM('Vegetarian', 'Non-Vegetarian', 'Vegan'),
    FOREIGN KEY (user_id) REFERENCES UserAuthentication(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS FitnessGoals (
    goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    goal_type ENUM('Weight Loss', 'Muscle Gain', 'Maintain Weight', 'Endurance'),
    target_weight DECIMAL(5,2),
    target_body_fat DECIMAL(4,2),
    target_date DATE,
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
    FOREIGN KEY (user_id) REFERENCES UserAuthentication(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    provider_name VARCHAR(100),
    appointment_type VARCHAR(50),
    appointment_date DATETIME,
    notes TEXT,
    FOREIGN KEY (user_id) REFERENCES UserAuthentication(user_id) ON DELETE CASCADE
);
