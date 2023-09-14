package com.example.credit_service_project.dto.cardDTO;

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
