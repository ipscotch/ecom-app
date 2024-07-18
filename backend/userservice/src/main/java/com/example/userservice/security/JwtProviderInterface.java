package com.example.userservice.security;

public interface JwtProviderInterface {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);

}

