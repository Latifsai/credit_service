package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.DeleteAccountResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAndDeleteAccountRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.exeption.ErrorsMessage;
import com.example.credit_service_project.service.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImp implements AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> {

    private final AccountRepository repository;

    @Override
    public DeleteAccountResponse execute(SearchAndDeleteAccountRequest request) {
        var account = repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber());
        return account.map(value -> new DeleteAccountResponse(value.getId(), value.getAccountNumber(),
                        value.getStatus(), value.getBalance()))
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }
}
