package com.example.credit_service_project.dto.user;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class UserResponseDTO {
    UUID id;
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
