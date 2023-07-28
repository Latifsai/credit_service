package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/account")
public class AccountController {
    private final AccountService<AddAccountDTOResponse, AddAccountDTORequest> createService;
    private final AccountService<GetAccountsListResponse, GetAccountsListRequest> getService;
    private final AccountService<SearchAccountResponse, SearchAndDeleteAccountRequest> searchService;
    private final AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> deleteService;
    private final AccountService<UpdateAccountResponse, UpdateAccountRequest> updateService;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody AddAccountDTORequest request) {
        var response = createService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAccountList(@RequestBody GetAccountsListRequest request) {
        var response = getService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAccount(@RequestBody SearchAndDeleteAccountRequest request) {
        var response = searchService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(@RequestBody SearchAndDeleteAccountRequest request) {
        var response = deleteService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountRequest request) {
        var response = updateService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
