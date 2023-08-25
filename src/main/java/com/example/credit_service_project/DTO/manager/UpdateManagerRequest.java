package com.example.credit_service_project.DTO.manager;

import lombok.Value;

import java.util.UUID;

@Value
public class UpdateManagerRequest {
    UUID id;
    String surname;
    String email;
}
