package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;

public class DTOManagerCreator {

    public static ManagerResponseDTO getResponseDTO() {
        return new ManagerResponseDTO(
                "Ivan",
                "Semyonov",
                "ivan_manager@loewen.de"
        );
    }

    public static ManagerResponseDTO getUpdatedResponseDTO() {
        return new ManagerResponseDTO(
                "Ivan",
                "Simonov",
                "main_manager@loewen.de"
        );
    }
}
