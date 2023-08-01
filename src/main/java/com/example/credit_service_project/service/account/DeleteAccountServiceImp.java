package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.DeleteAccountResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAndDeleteAccountRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImp implements AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> {

    private final AccountRepository repository;

    @Override
    public DeleteAccountResponse execute(SearchAndDeleteAccountRequest request) {
        var account = repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber());
        return account.map(a -> {
            var response = new DeleteAccountResponse(a.getId(), a.getAccountNumber(), a.getStatus(), a.getBalance());
            repository.delete(a);
            return response;
        }).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }
}
