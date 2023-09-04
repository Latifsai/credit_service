package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;

import java.util.UUID;

public class DTOManagerCreator {

    public static ManagerResponseDTO getResponseDTO() {
        return new ManagerResponseDTO(
                UUID.fromString("55553333-0000-4444-b491-426655440000"),
                "Ivan",
                "Semyonov",
                "ivan_manager@loewen.de"
        );
    }

    public static ManagerResponseDTO getUpdatedResponseDTO() {
        return new ManagerResponseDTO(
                UUID.fromString("55553333-0000-4444-b491-426655440000"),
                "Ivan",
                "Simonov",
                "main_manager@loewen.de"
        );
    }
}
