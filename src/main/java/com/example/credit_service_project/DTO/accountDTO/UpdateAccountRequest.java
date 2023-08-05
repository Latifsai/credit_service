package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateAccountRequest {
    @NotNull(message = "AccountID must be not null!")
    UUID accountID;
    @NotNull(message = "accountNumber must be not null!")
    @NotEmpty(message = "accountNumber must be not empty!")
    String accountNumber;
    //to change
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    BigDecimal unpaidLoanDebt;
    BigDecimal unpaidPercentageLoanDebt;
}
