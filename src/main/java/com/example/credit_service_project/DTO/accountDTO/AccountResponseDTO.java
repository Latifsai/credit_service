package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class AccountResponseDTO {
     UUID id;
     String accountNumber;
     String clientInitial;
     BigDecimal loanDebt;
     BigDecimal percentageDebt;
     AccountStatus status;
     BigDecimal balance;
     LocalDate closingDate;
     BigDecimal unpaidCreditSum;
     String currency;
     String country;
}
