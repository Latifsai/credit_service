package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCardDTORequest {
     @NotNull(message = "ID must not be null!")
     UUID id;

     BigDecimal balance;
     String deliveryAddress;
     CardStatus cardStatus;
}
