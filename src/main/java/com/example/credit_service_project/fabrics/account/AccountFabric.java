package com.example.credit_service_project.fabrics.account;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.account.*;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFabric implements AccountAbstractFabric {

    private final AccountRepository repository;
    private final SearchClientServiceImp searchClientService;
    private final SearchAccountsServiceImp searchAccountsService;
    private final CreateAccountServiceImp createAccountService;
    private final AccountUtil util;

    @Override
    public AccountService<AccountDTOResponse, AddAccountDTORequest> activeCreateService() {
        return new CreateAccountServiceImp(repository, searchClientService, util);
    }

    @Override
    public GetAccountsListServiceImp activeGetService() {
        return new GetAccountsListServiceImp(repository, util);
    }


    @Override
    public AccountService<AccountDTOResponse, SearchAccountRequest> activeSearchService() {
        return new SearchAccountsServiceImp(repository, util);
    }


    @Override
    public AccountService<AccountDTOResponse, UpdateAccountRequest> activeUpdateService() {
        return new UpdateAccountServiceImp(searchAccountsService, createAccountService, util);
    }
}
