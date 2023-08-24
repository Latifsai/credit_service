package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.services.account.CreateAccountServiceImp;
import com.example.credit_service_project.services.account.GetAccountsListServiceImp;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.account.UpdateAccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final CreateAccountServiceImp create;
    private final GetAccountsListServiceImp get;
    private final SearchAccountsServiceImp search;
    private final UpdateAccountServiceImp update;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTOResponse createNewAccount(@RequestBody AddAccountDTORequest request) {
        return create.execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AccountDTOResponse> getAccountList() {
        return get.execute();

    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDTOResponse searchAccount(@RequestBody SearchAccountRequest request) {
        return search.execute(request);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDTOResponse updateAccount(@RequestBody UpdateAccountRequest request) {
        return update.execute(request);
    }

}
