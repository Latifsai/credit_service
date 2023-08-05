package com.example.credit_service_project.DTO.manager;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateManagerRequest {
    @NotNull(message = "ID must not be null!")
    UUID id;

    @NotNull(message = "surname must not be null!")
    @NotEmpty(message = "surname must not be empty!")
    String surname;

    @Email(message = "Format must match email!")
    String email;
}
