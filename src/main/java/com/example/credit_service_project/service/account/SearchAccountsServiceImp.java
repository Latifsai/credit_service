package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service

@RequiredArgsConstructor
public class SearchAccountsServiceImp implements AccountService<AccountDTOResponse, SearchAccountRequest> {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Transactional(readOnly = true)
    @Override
    public AccountDTOResponse execute(SearchAccountRequest request) {
        Account account = findAccountByIdOrNumber(request.getId(), request.getAccountNumber());
        return util.convertAccountToAddResponse(account);
    }

    public Account findAccountByIdOrNumber(UUID id, String number){
        return repository.findByIdOrAccountNumber(id, number)
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }
}
