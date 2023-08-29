package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.client.ClientSearchService;
import com.example.credit_service_project.services.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCreationService {

    /**
     * In this service, accounts will be created and saved to the database
     */

    private final AccountRepository repository;
    private final ClientSearchService searchClientService;
    private final AccountUtil util;

    public AccountDTOResponse createAccount(AddAccountDTORequest request) {
        Client client = searchClientService.findClientById(request.getClientId());
        Account account = util.convertAddRequestToAccount(request, client);
        Account savedAccount = saveAccount(account);
        log.info("Create and save account: {}", savedAccount); // перписать дать часть информаации необходимую
        return util.convertAccountToAddResponse(savedAccount);
    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
