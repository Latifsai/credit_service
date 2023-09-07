package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.user.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DTOUserCreator {
    public static UserResponseDTO getResponse() {
        return  UserResponseDTO.builder()
                .id(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"))
                .name("John")
                .surname("Snow")
                .income(new BigDecimal("2500"))
                .expenses(new BigDecimal("1500"))
                .address("Johan's Str 34")
                .email("john_manager@loewen.de")
                .phone("+49 176 28835002")
                .registrationDate(LocalDate.of(2021, 3,14))
                .updateDate(LocalDate.of(2021, 3,14))
                .build();
    }

    public static UserResponseDTO getUpdateResponse() {
        return  UserResponseDTO.builder()
                .id(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"))
                .name("John")
                .surname("Snow")
                .income(new BigDecimal("4000"))
                .expenses(new BigDecimal("2000"))
                .address("Johan's Str 34")
                .email("john_manager@loewen.de")
                .phone("+49 176 28835002")
                .registrationDate(LocalDate.of(2021, 3,14))
                .updateDate(LocalDate.of(2023, 9,7))
                .build();
    }
}
