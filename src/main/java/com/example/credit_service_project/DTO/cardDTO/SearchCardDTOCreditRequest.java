package com.example.credit_service_project.DTO.cardDTO;

import lombok.Value;

@Value
public class SearchCardDTOCreditRequest {
    private String cardNumber;
    private String holderName;
}
