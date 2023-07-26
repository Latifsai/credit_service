package com.example.credit_service_project.controllers;


import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTOResponse;
import com.example.credit_service_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/account")
public class AccountController {
    private final AccountService<AddAccountDTOResponse,AddAccountDTORequest> createService;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody AddAccountDTORequest request) {
        var response = createService.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
