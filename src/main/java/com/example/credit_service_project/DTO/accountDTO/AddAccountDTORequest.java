package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AddAccountDTORequest {
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    BigDecimal balance;
    String currency;
    AccountStatus status;

    Integer accountNumberLength;
    Long yearsAmountForClosingDate;
}
