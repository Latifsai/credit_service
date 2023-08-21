package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAccountsListServiceImp {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Transactional(readOnly = true)
    public List<AccountDTOResponse> execute() {
        return repository.findAll().stream()
                .map(util::convertAccountToAddResponse)
                .toList();
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }
}
