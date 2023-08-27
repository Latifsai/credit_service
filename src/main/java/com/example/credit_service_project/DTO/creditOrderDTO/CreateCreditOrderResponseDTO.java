package com.example.credit_service_project.DTO.creditOrderDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateCreditOrderResponseDTO {
    BigInteger productID;
    String productName;
    UUID id;
    String number;
    BigDecimal amount;
    LocalDate creationDate;
    Integer maxPeriodMonths;
    Integer minPeriodMonths;
    CreditOrderStatus creditOrderStatus;
    String currency;
}