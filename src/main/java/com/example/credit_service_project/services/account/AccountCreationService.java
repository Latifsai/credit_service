package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.CreateAccountDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.client.ClientSearchService;
import com.example.credit_service_project.services.utils.AccountUtil;
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
    private final ClientSearchService searchClientService;
    private final AccountUtil util;

    public AccountDTOResponse createAccount(CreateAccountDTORequest request) {
        Client client = searchClientService.findClientById(request.getClientId());
        Account account = util.convertAddRequestToAccount(request, client);
        Account savedAccount = saveAccount(account);
        log.info("Create and save account with number: {}", savedAccount.getAccountNumber()); // перписать дать часть информаации необходимую
        return util.convertAccountToAddResponse(savedAccount);
    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
