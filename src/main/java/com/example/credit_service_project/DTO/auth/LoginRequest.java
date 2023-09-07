package com.example.credit_service_project.DTO.auth;

import lombok.Value;

@Value
public class LoginRequest {
    String username;
    String password;
}
