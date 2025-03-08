package com.app.HealthSphere.service;

import com.app.HealthSphere.model.User;
import com.app.HealthSphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save user
    public int saveUser(User user) {
        return userRepository.save(user);
    }

    // Find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Find user by ID
    public User findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Update user
    public int updateUser(User user) {
        return userRepository.update(user);
    }

    // Delete user
    public int deleteUser(Long userId) {
        return userRepository.delete(userId);
    }
}
