package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.services.AccountService;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorException;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountStatusException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAccountServiceImp implements AccountService<AccountDTOResponse, UpdateAccountRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final CreateAccountServiceImp createAccountService;
    private final AccountUtil util;

    @Override
    public AccountDTOResponse execute(UpdateAccountRequest request) {
        Account accountToFind = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (accountToFind.getStatus().equals(AccountStatus.ACTIVE)) {
            Account updatedAccount = util.updateAccount(accountToFind, request);
            createAccountService.saveAccount(updatedAccount);
            return util.convertAccountToAddResponse(updatedAccount);
        }else {
            throw new AccountStatusException(ErrorsMessage.ACCOUNT_STATUS_EXCEPTION_MESSAGE);
        }
    }

    public void saveUpdatedAccount(Account updatedAccount) {
        createAccountService.saveAccount(updatedAccount);
    }
}
