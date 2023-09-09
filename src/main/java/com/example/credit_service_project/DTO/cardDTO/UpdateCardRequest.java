package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCardRequest {
     UUID id;
     BigDecimal balance;
     String deliveryAddress;
     CardStatus cardStatus;
}
