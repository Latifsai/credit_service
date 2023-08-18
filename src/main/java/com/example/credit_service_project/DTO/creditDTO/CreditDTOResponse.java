package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreditDTOResponse {
    UUID id;
    String creditType;
    BigDecimal creditSum;
    BigDecimal interestRate;
    Integer periodMonth;
    BigDecimal fine;
    boolean needDeposit;
    CreditStatus creditStatus;
    String currency;
    BigDecimal rateBase;





}
