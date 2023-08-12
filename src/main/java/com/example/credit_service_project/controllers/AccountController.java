package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.fabrics.account.AccountFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountFabric fabric;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTOResponse createNewAccount(@RequestBody AddAccountDTORequest request) {
        return fabric.activeCreateService().execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AccountDTOResponse> getAccountList() {
        return fabric.activeGetService().execute();

    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDTOResponse searchAccount(@RequestBody SearchAccountRequest request) {
        return fabric.activeSearchService().execute(request);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDTOResponse updateAccount(@RequestBody UpdateAccountRequest request) {
        return fabric.activeUpdateService().execute(request);
    }

}
