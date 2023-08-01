package com.example.credit_service_project.fabrics.account;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.service.AccountService;

public interface AccountAbstractFabric {
    AccountService<AddAccountDTOResponse, AddAccountDTORequest> activeCreateService();

    AccountService<GetAccountsListResponse, GetAccountsListRequest> activeGetService();

    AccountService<SearchAccountResponse, SearchAndDeleteAccountRequest> activeSearchService();

    AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> activeDeleteService();

    AccountService<UpdateAccountResponse, UpdateAccountRequest> activeUpdateService();
}
