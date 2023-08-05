package com.example.credit_service_project.DTO.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateClientRequest {
    @NotNull(message = "ID must bot be null!")
    UUID id;

    @Positive(message = "income must be only positive!")
    BigDecimal income;

    @Positive(message = "expenses must be only positive!")
    BigDecimal expenses;
}
