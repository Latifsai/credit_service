package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountsListResponse {
    List<Account> accounts;
}
