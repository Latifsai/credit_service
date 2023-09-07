package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.DTO.accountDTO.CreateAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.services.account.AccountCreationService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.account.GetAllAccountsService;
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
    private final AccountUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createNewAccount(@RequestBody CreateAccountDTORequest request) {
        return create.createAccount(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AccountResponseDTO> getAccountList() {
        return get.getAllAccounts();

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDTO updateAccount(@RequestBody UpdateAccountRequest request) {
        return update.updateAccount(request);
    }

}
