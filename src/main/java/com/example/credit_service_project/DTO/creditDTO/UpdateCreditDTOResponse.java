package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCreditDTOResponse {
    UUID id;
    BigDecimal creditSum;
    BigDecimal interestRate;
    String rateBase;

    String creditType;
    boolean needDeposit;
    Integer periodMonth;
    CreditStatus creditStatus;
    String currency;
}
