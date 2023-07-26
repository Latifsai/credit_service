package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;


@Value
public class AddAndSearchCreditDTOResponse {
    UUID id;
    String creditType;
    BigDecimal creditSum;
    BigDecimal interestRate;
    BigDecimal annualPercentage;
    BigDecimal fine;
    boolean needDeposit;
    String rateBase;
    Integer periodMonth;
    CreditStatus creditStatus;
    String currency;
}
