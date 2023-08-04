package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAccountServiceImp implements AccountService<UpdateAccountResponse, UpdateAccountRequest> {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Override
    public UpdateAccountResponse execute(UpdateAccountRequest request) {
        var accountToFind = repository.findByAccountNumber(request.getAccountNumber());
        if (accountToFind.isPresent()) {
            var updatedAccount = util.updateAccount(accountToFind.get(), request);
            saveUpdatedAccount(updatedAccount);
            return util.convertTotUpdateResponse(updatedAccount);
        }
        throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);
    }

    public void saveUpdatedAccount(Account updatedAccount) {
        repository.save(updatedAccount);
    }
}
