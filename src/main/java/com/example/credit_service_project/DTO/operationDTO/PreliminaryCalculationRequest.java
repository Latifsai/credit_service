package com.example.credit_service_project.DTO.operationDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Value
public class PreliminaryCalculationRequest {
    UUID accountID;
    String accountNumber;
    BigInteger productID;
    Integer monthTerm;
    BigDecimal interestRate;
}
