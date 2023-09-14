package com.example.credit_service_project.dto.creditDTO;

import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class CreditResponseDTO {
    UUID id;
    String creditType;
    BigDecimal creditSum;
    BigDecimal interestRate;
    Integer periodMonth;
    BigDecimal fine;
    boolean needDeposit;
    CreditStatus creditStatus;
    String currency;





}
