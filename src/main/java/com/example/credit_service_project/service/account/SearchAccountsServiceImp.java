package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.AccountNotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchAccountsServiceImp implements AccountService<AccountDTOResponse, SearchAccountRequest> {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Override
    public AccountDTOResponse execute(SearchAccountRequest request) {
        Optional<Account> account = findAccountByIdOrNumber(request.getId(), request.getAccountNumber());
        return account.map(util::convertAccountToAddResponse)
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }

    public Optional<Account> findAccountByIdOrNumber(UUID id, String number){
        return repository.findByIdOrAccountNumber(id, number);
    }
}
