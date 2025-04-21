package com.example.assurance2.Controller;

public class AuthResponse {
    private String token;
    private Long expirationTime;

    public AuthResponse(String token, Long expirationTime) {
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }
}