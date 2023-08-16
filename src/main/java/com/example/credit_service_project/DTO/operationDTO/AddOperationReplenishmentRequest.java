package com.example.credit_service_project.DTO.operationDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AddOperationReplenishmentRequest {
    UUID accountID;
    String number;
    BigDecimal sum;
}
