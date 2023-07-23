package com.example.credit_service_project.DTO.cardDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class DeleteCardDTOResponse {
     UUID id;
     String cardNumber;
     String accountNumber;

}
