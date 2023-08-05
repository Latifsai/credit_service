package com.example.credit_service_project.DTO.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AddClientRequest {
    @NotNull(message = "managerID must be not null!")
    UUID managerID;

    @NotNull(message = "name must be not null!")
    @NotEmpty(message = "name must be not empty!")
    String name;

    @NotNull(message = "surname must be not null!")
    @NotEmpty(message = "surname must be not empty!")
    String surname;

    @Positive(message = "income must be always positive!")
    BigDecimal income;

    @Positive(message = "expenses must be always positive!")
    BigDecimal expenses;
}
