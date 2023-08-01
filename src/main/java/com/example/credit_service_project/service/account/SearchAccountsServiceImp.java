package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.SearchAccountResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAndDeleteAccountRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchAccountsServiceImp implements AccountService<SearchAccountResponse, SearchAndDeleteAccountRequest> {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Override
    public SearchAccountResponse execute(SearchAndDeleteAccountRequest request) {
        var account = repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber());
        return account.map(util::convertAccountToSearchResponse).
                orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }
}
