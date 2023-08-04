package com.example.credit_service_project.DTO.manager;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class AddManagerRequest {
    @NotNull(message = "Name must not be null!")
    @NotEmpty(message = "Name must not be empty!")
    String name;
    @NotNull(message = "Surname must not be null!")
    @NotEmpty(message = "Surname must not be empty!")
    String surname;
    @Email
    String email;
}
