package com.example.credit_service_project.fabrics.account;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.account.*;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFabric implements AccountAbstractFabric {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Override
    public AccountService<AddAccountDTOResponse, AddAccountDTORequest> activeCreateService() {
        return new CreateAccountServiceImp(repository, util);
    }

    @Override
    public AccountService<GetAccountsListResponse, GetAccountsListRequest> activeGetService() {
        return new GetAccountsListServiceImp(repository);
    }

    @Override
    public AccountService<SearchAccountResponse, SearchAndDeleteAccountRequest> activeSearchService() {
        return new SearchAccountsServiceImp(repository, util);
    }

    @Override
    public AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> activeDeleteService() {
        return new DeleteAccountServiceImp(repository);
    }

    @Override
    public AccountService<UpdateAccountResponse, UpdateAccountRequest> activeUpdateService() {
        return new UpdateAccoutnServiceImp(repository, util);
    }
}
