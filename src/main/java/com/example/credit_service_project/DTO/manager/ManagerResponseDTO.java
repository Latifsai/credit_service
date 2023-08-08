package com.example.credit_service_project.DTO.manager;

import lombok.Value;

import java.util.UUID;

@Value
public class ManagerResponseDTO {
    UUID id;
    String name;
    String surname;
    String email;
}
