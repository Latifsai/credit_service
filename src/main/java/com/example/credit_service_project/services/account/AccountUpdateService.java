package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountStatusException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountUpdateService {

    private final AccountSearchService accountSearchService;
    private final AccountCreationService accountCreationService;
    private final AccountUtil util;

    public AccountDTOResponse updateAccount(UpdateAccountRequest request) {
        Account accountToFind = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (accountToFind.getStatus().equals(AccountStatus.ACTIVE)) {
            Account updatedAccount = util.updateAccount(accountToFind, request);
            accountCreationService.saveAccount(updatedAccount);
            return util.convertAccountToAddResponse(updatedAccount);
        }else {
            throw new AccountStatusException(ErrorsMessage.ACCOUNT_STATUS_EXCEPTION_MESSAGE);
        }
    }

    public void saveUpdatedAccount(Account updatedAccount) {
        accountCreationService.saveAccount(updatedAccount);
    }
}
