package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountsListRequest {
    private AccountStatus status;
}
