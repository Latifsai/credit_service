package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCardDTOResponse {
     UUID id;
     String cardNumber;
     String holderName;
     BigDecimal balance;
     String deliveryAddress;
     CardStatus cardStatus;
}
