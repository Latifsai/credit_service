package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.Value;

@Value
public class SearchAndDeleteAccountRequest {
    String accountNumber;
    AccountStatus status;
}
