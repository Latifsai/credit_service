package com.example.credit_service_project.fabrics.account;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.account.GetAccountsListServiceImp;

public interface AccountAbstractFabric {
    AccountService<AddAccountDTOResponse, AddAccountDTORequest> activeCreateService();

    GetAccountsListServiceImp activeGetService();

    AccountService<AccountResponseDTO, SearchAccountRequest> activeSearchService();


    AccountService<UpdateAccountResponse, UpdateAccountRequest> activeUpdateService();
}
