package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountResponse {
    UUID id;
    String accountNumber;
    //to change
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    LocalDateTime closingDate;
    BigDecimal unpaidLoanDebt;
    BigDecimal unpaidPercentageLoanDebt;
    String currency;
}
