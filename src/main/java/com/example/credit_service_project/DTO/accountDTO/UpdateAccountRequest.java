package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateAccountRequest {
    UUID accountID;
    String accountNumber;
    //to change
    BigDecimal loanDebt;
    BigDecimal percentageDebt;
    AccountStatus status;
    BigDecimal balance;
    BigDecimal unpaidCreditSum;
    String country;
}
