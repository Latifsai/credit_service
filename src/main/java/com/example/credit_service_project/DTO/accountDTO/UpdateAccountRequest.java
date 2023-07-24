package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class UpdateAccountRequest {
    String accountNumber;

    //to change
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    LocalDateTime closingDate;
    BigDecimal unpaidLoanDebt;
    BigDecimal unpaidPercentageLoanDebt;
}
