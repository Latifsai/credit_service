package com.example.credit_service_project.DTO.client;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class ClientResponseDTO {
    UUID id;
    UUID managerId;
    String managerEmail;
    String name;
    String surname;
    BigDecimal income;
    BigDecimal expenses;
    String address;
    String email;
    String phone;
    LocalDate registrationDate;
    LocalDate updateDate;
}
