package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtProvider;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private JwtProvider jwtProvider;

    public User registerUser(User user) {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        rabbitTemplate.convertAndSend("user-exchange", "user.created", savedUser);
        return savedUser;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String signinUser(String username, String password) throws Exception {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationToken.getPrincipal(), authenticationToken.getCredentials()));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            
            if (authentication.isAuthenticated()) {
                return jwtProvider.generateToken(username);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }

        
    }

    public boolean validateToken(String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        return jwtProvider.validateToken(jwt);
    }
}
