package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCardDTORequest {
     UUID accountID;
     String accountNumber;

     UUID id;
     BigDecimal balance;
     String deliveryAddress;
     CardStatus cardStatus;
}
