package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.fabrics.account.AccountFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountFabric fabric;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody AddAccountDTORequest request) {
        var response = fabric.activeCreateService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAccountList() {
        var response = fabric.activeGetService().execute();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAccount(@RequestBody SearchAccountRequest request) {
        var response = fabric.activeSearchService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountRequest request) {
        var response = fabric.activeUpdateService().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
