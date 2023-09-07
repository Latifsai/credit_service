package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountSearchService {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Transactional(readOnly = true)
    public AccountResponseDTO searchAccount(SearchAccountRequest request) {
        Account account = findAccountByIdOrNumber(request.getId(), request.getAccountNumber());
        log.info("Search account with ID {} and with number {}",account.getId(), account.getAccountNumber());
        return util.convertAccountToAddResponse(account);
    }

    public Account findAccountByIdOrNumber(UUID id, String number) {
        return repository.findByIdOrAccountNumber(id, number)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
    }
}
