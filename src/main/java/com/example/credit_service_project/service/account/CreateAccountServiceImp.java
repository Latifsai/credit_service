package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.ClientNotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CreateAccountServiceImp implements AccountService<AccountDTOResponse, AddAccountDTORequest> {
    private final AccountRepository repository;
    private final SearchClientServiceImp searchClientService;
    private final AccountUtil util;

    @Override
    public AccountDTOResponse execute(AddAccountDTORequest request) {
        Client clientOptional = searchClientService.findClientById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(ErrorsMessage.UNABLE_TO_ADD_ACCOUNT_MESSAGE));

        var account = util.convertAddRequestToAccount(request, clientOptional);
        Account savedAccount = saveAccount(account);
        return util.convertAccountToAddResponse(savedAccount);


    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
