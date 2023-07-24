package com.example.credit_service_project.DTO.accountDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class AddAccountDTORequest {
    BigDecimal loanDebt;
    BigDecimal balance;
    LocalDateTime openingDate;
    String currency;
}
