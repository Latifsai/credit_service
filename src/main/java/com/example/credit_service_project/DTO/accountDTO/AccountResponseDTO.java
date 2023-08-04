package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private UUID id;
    private String accountNumber;
    private AccountStatus status;
    private BigDecimal balance;
    private BigDecimal loanDebt;
    private BigDecimal unpaidLoanDebt;
    private BigDecimal unpaidPercentageLoanDebt;
    private String currency;
}
