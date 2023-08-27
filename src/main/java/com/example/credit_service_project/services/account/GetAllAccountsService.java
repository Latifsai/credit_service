package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllAccountsService {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Transactional(readOnly = true)
    public List<AccountDTOResponse> getAllAccounts() {
        log.info("Get a list of accounts");
        return repository.findAll().stream()
                .map(util::convertAccountToAddResponse)
                .toList();
    }

    public List<Account> findAllAccounts() {
        return repository.findAll();
    }
}