package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.service.generator.AccountGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountUtil {

    public Account convertAddRequestToAccount(AddAccountDTORequest request, Client client) {
        Account account = new Account();
        account.setClient(client);
        account.setCountry(request.getCounty());
        account.setAccountNumber(AccountGenerator.createRandomAccountNumber(request.getAccountNumberLength()));
        account.setLoanDebt(request.getLoanDebt());
        account.setPercentageDebt(request.getPercentageDebt());
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(request.getBalance());
        account.setOpeningDate(AccountGenerator.createOpeningDay());
        account.setClosingDate(AccountGenerator.LocalDateCreateClosingDate(request.getYearsAmountForClosingDate()));
        account.setUnpaidLoanDebt(AccountGenerator.getUnpaidLoanDebt(request));
        account.setUnpaidPercentageLoanDebt(AccountGenerator.getUnpaidPercentageLoanDebt(request));
        account.setCurrency(request.getCurrency());
        return account;
    }

    public AccountDTOResponse convertAccountToAddResponse(Account account) {
        return new AccountDTOResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getClient().getName() + " " + account.getClient().getSurname(),
                account.getLoanDebt(),
                account.getPercentageDebt(),
                account.getStatus(),
                account.getBalance(),
                account.getClosingDate(),
                account.getUnpaidLoanDebt(),
                account.getUnpaidPercentageLoanDebt(),
                account.getCurrency(),
                account.getCountry()
                );
    }


    public Account updateAccount(Account account, UpdateAccountRequest request) {
        if (request.getLoanDebt() != null) account.setLoanDebt(request.getLoanDebt());
        if (request.getPercentageDebt() != null) account.setPercentageDebt(request.getPercentageDebt());
        if (request.getStatus() != null) account.setStatus(request.getStatus());
        if (request.getBalance() != null) account.setBalance(request.getBalance());
        if (request.getUnpaidLoanDebt() != null) account.setUnpaidLoanDebt(request.getUnpaidLoanDebt());
        if (request.getUnpaidPercentageLoanDebt() != null) account.setUnpaidPercentageLoanDebt(request.getUnpaidPercentageLoanDebt());
        if (request.getCountry() != null && !request.getCountry().trim().isEmpty()) account.setCountry(request.getCountry());
        return account;
    }

}
