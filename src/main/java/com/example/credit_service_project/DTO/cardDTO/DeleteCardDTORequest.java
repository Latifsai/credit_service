package com.example.credit_service_project.DTO.cardDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class DeleteCardDTORequest {
    private UUID id;
    private String cardNumber;
}
