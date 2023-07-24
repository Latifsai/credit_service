package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class DeleteAccountResponse {
    UUID id;
    String accountNumber;
    AccountStatus status;
    BigDecimal balance;
}
