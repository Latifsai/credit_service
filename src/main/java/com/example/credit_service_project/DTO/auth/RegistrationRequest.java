package com.example.credit_service_project.DTO.auth;


import lombok.Value;

@Value
public class RegistrationRequest {
    String name;
    String password;
    String confirmPassword;
}
