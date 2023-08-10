package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.AccountNotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAccountServiceImp implements AccountService<AccountDTOResponse, UpdateAccountRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final CreateAccountServiceImp createAccountService;
    private final AccountUtil util;

    @Override
    public AccountDTOResponse execute(UpdateAccountRequest request) {
        Account accountToFind = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
        var updatedAccount = util.updateAccount(accountToFind, request);
        createAccountService.saveAccount(updatedAccount);
        return util.convertAccountToAddResponse(updatedAccount);
    }

    public void saveUpdatedAccount(Account updatedAccount) {
        createAccountService.saveAccount(updatedAccount);
    }
}
