package com.example.credit_service_project.dto.operationDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class PreliminaryCalculationRequest {
    UUID accountID;
    String accountNumber;
    Long productID;
    Integer monthTerm;
    BigDecimal interestRate;
}
