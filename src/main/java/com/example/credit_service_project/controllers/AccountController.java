package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.CreateAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.services.account.AccountCreationService;
import com.example.credit_service_project.services.account.GetAllAccountsService;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountCreationService create;
    private final GetAllAccountsService get;
    private final AccountSearchService search;
    private final AccountUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTOResponse createNewAccount(@RequestBody CreateAccountDTORequest request) {
        return create.createAccount(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AccountDTOResponse> getAccountList() {
        return get.getAllAccounts();

    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDTOResponse searchAccount(@RequestBody SearchAccountRequest request) {
        return search.searchAccount(request);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDTOResponse updateAccount(@RequestBody UpdateAccountRequest request) {
        return update.updateAccount(request);
    }

}
