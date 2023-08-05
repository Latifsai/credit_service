package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAccountServiceImp implements AccountService<AccountDTOResponse, UpdateAccountRequest> {

    private final AccountRepository repository;
    private final AccountUtil util;

    @Override
    public AccountDTOResponse execute(UpdateAccountRequest request) {
        Optional<Account> accountToFind = repository.findByIdOrAccountNumber(request.getAccountID(), request.getAccountNumber());
        if (accountToFind.isPresent()) {
            var updatedAccount = util.updateAccount(accountToFind.get(), request);
            saveUpdatedAccount(updatedAccount);
            return util.convertAccountToAddResponse(updatedAccount);
        }
        throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);
    }

    public void saveUpdatedAccount(Account updatedAccount) {
        repository.save(updatedAccount);
    }
}
