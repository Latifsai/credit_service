package com.example.credit_service_project.DTO.cardDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class SearchCardDTOCreditRequest {
     @NotNull(message = "ID must not be null!")
     UUID id;
     @NotNull(message = "CardNumber must not be null!")
     @NotEmpty(message = "CardNumber must not be empty!")
     String cardNumber;
}
