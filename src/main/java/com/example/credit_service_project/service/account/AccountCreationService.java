package com.example.credit_service_project.service.account;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.user.UserSearchService;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * In this service, accounts will be created and saved to the database
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCreationService {

    private final AccountRepository repository;
    private final UserSearchService searchClientService;
    private final AccountUtil util;

    public AccountResponseDTO createAccount(CreateAccountRequestDTO request) {
        User user = searchClientService.findUserById(request.getClientId());
        Account account = util.convertAddRequestToAccount(request, user);
        Account savedAccount = saveAccount(account);
        log.info("Create and save account with number: {}", savedAccount.getAccountNumber());
        return util.convertAccountToAddResponse(savedAccount);
    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
