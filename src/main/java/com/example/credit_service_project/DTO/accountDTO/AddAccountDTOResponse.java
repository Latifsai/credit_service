package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class AddAccountDTOResponse {
    UUID id;
    String accountNumber;
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    LocalDateTime openingDate;
    LocalDateTime closingDate;
    BigDecimal unpaidLoanDebt;
    BigDecimal unpaidPercentageLoanDebt;
    String currency;
}
