package com.example.credit_service_project.service.account;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.utils.AccountUtil;
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
    public List<AccountResponseDTO> getAllAccounts() {
        log.info("Get a list of accounts");
        return repository.findAll().stream()
                .map(util::convertAccountToAddResponse)
                .toList();
    }

    public List<Account> findAllAccounts() {
        return repository.findAll();
    }
}
