package com.example.credit_service_project.dto.auth;

import lombok.Value;

@Value
public class LoginRequest {
    String username;
    String password;
}
