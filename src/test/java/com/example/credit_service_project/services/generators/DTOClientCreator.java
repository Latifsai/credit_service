package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.user.UserResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class DTOClientCreator {
    public static UserResponseDTO getResponse() {
        return new UserResponseDTO(
                UUID.fromString("88882222-6666-4444-b491-426655440000"),
                UUID.fromString("55553333-0000-4444-b491-426655440000"),
                "ivan_manager@loewen.de",
                "Lew",
                "Kim",
                new BigDecimal("2500"),
                new BigDecimal("1500")
        );
    }

    public static UserResponseDTO getUpdateResponse() {
        return new UserResponseDTO(
                UUID.fromString("88882222-6666-4444-b491-426655440000"),
                UUID.fromString("55553333-0000-4444-b491-426655440000"),
                "ivan_manager@loewen.de",
                "Lew",
                "Kim",
                new BigDecimal("3500"),
                new BigDecimal("2000")
        );
    }
}
