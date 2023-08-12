package com.example.credit_service_project.DTO.manager;

import lombok.Value;

@Value
public class AddManagerRequest {
    String name;
    String surname;
    String email;
}
