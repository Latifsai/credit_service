package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    private String accountNumber;
    //to change
    private BigDecimal loanDebt;
    private BigDecimal percentageDebt;
    private AccountStatus status;
    private BigDecimal balance;
    private BigDecimal unpaidLoanDebt;
    private BigDecimal unpaidPercentageLoanDebt;
}
