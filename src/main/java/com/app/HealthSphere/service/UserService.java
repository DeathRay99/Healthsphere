package com.app.HealthSphere.service;

import com.app.HealthSphere.exception.UserNotFoundException;
import com.app.HealthSphere.model.User;
import com.app.HealthSphere.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(Long userId, User user) {
            userRepository.save(userId, user);
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
        return users;
    }


    public User findUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    public void updateUser(User user) {
        try {
            userRepository.findById(user.getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " not found");
        }
        userRepository.update(user);
    }


    public void deleteUser(Long id) {
        try {
            userRepository.findById(id); // Just to check if user exists
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.delete(id);
    }

}
