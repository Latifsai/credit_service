package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.fabrics.account.AccountFabric;
import com.example.credit_service_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/account")
public class AccountController {
    private final AccountFabric fabric;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody AddAccountDTORequest request) {
        var response = fabric.activeCreateService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAccountList(@RequestBody GetAccountsListRequest request) {
        var response = fabric.activeGetService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAccount(@RequestBody SearchAndDeleteAccountRequest request) {
        var response = fabric.activeSearchService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(@RequestBody SearchAndDeleteAccountRequest request) {
        var response = fabric.activeDeleteService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountRequest request) {
        var response = fabric.activeUpdateService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
