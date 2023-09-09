package com.example.credit_service_project.DTO.accountDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateAccountRequestDTO {
     UUID clientId;
     String country;
     BigDecimal balance;
     String currency;
     Integer accountNumberLength;
     Integer yearsAmountForClosingDate;
}
