package com.strikes.cognitivenotes.controller;

import com.strikes.cognitivenotes.model.User;
import com.strikes.cognitivenotes.security.JwtUtil;
import com.strikes.cognitivenotes.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid   @RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String rePassword = request.get("rePassword");

        User registeredUser = userService.registerUser(username, password, rePassword);
        return ResponseEntity.ok(Map.of("message", "User registered successfully!", "username", registeredUser.getUsername()));
    }

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Spring Security ke zariye authentication check karna
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {
            // Authentication se UserDetails nikal kar JwtUtil ko dein
            org.springframework.security.core.userdetails.UserDetails userDetails = 
                (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
            
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(Map.of("token", token));
        }

        // Compiler ko satisfy karne ke liye yeh line jodein
        throw new RuntimeException("Invalid username or password");
    
    }
}