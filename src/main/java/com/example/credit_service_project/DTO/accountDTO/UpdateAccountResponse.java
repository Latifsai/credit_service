package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountResponse {
    private UUID id;
    private String accountNumber;
    //to change
    private BigDecimal loanDebt;
    private BigDecimal percentageDebt;
    private AccountStatus status;
    private BigDecimal balance;
    private LocalDate closingDate;
    private BigDecimal unpaidLoanDebt;
    private BigDecimal unpaidPercentageLoanDebt;
    private String currency;
}
