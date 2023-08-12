package com.example.credit_service_project.DTO.client;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class ClientResponseDTO {
    UUID id;
    UUID managerId;
    String managerEmail;
    String name;
    String surname;
    BigDecimal income;
    BigDecimal expenses;
}