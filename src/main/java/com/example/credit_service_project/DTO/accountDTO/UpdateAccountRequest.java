package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    String accountNumber;
    //to change
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    BigDecimal unpaidLoanDebt;
    BigDecimal unpaidPercentageLoanDebt;
}
