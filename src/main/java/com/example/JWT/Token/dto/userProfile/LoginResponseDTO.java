package com.example.JWT.Token.dto.userProfile;

import java.time.LocalDateTime;

public class LoginResponseDTO {
    private String token;
    private LocalDateTime created_at;
    private String error;
    private String message;

    public LoginResponseDTO(String token, LocalDateTime created_at, String error, String message) {
        this.token = token;
        this.created_at = created_at;
        this.error = error;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
