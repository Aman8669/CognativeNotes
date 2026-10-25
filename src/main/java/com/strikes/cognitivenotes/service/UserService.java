package com.strikes.cognitivenotes.service;

import com.strikes.cognitivenotes.model.User;
import com.strikes.cognitivenotes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String rePassword) {
        System.out.println("-> UserService: Registering user: " + username);
        
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be null or empty");
        }

        // 1. Check Unique Username
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken! Please choose another one.");
        }

        // 2. Check Password Matching
        if (password == null || !password.equals(rePassword)) {
            throw new RuntimeException("Passwords do not match!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        
        return userRepository.save(user);
    }
}