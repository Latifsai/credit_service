package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.generator.AccountGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountUtil {

    private final AccountGenerator accountGenerator;

    public Account convertAddRequestToAccount(AddAccountDTORequest request) {
        Account account = new Account();
        account.setAccountNumber(accountGenerator.createRandomAccountNumber(request.getAccountNumberLength()));
        account.setLoanDebt(request.getLoanDebt());
        account.setPercentageDebt(request.getPercentageDebt());
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(request.getBalance());
        account.setOpeningDate(accountGenerator.createOpeningDay());
        account.setClosingDate(accountGenerator.createClosingDate(request.getYearsAmountForClosingDate()));
        account.setUnpaidLoanDebt(accountGenerator.getUnpaidLoanDebt(request));
        account.setUnpaidPercentageLoanDebt(accountGenerator.getUnpaidPercentageLoanDebt(request));
        account.setCurrency(request.getCurrency());
        return account;
    }
    public AddAccountDTOResponse convertAccountToAddResponse(Account account) {
        return new AddAccountDTOResponse(account.getId(), account.getAccountNumber(),
                account.getLoanDebt(), account.getPercentageDebt(), account.getStatus(),
                account.getBalance(),account.getOpeningDate(),account.getClosingDate(),
                account.getUnpaidLoanDebt(), account.getUnpaidPercentageLoanDebt(), account.getCurrency());
    }

    public SearchAccountResponse convertAccountToSearchResponse(Account account) {
        SearchAccountResponse response = new SearchAccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setStatus(account.getStatus());
        response.setBalance(account.getBalance());
        response.setLoanDebt(account.getLoanDebt());
        response.setUnpaidLoanDebt(account.getUnpaidLoanDebt());
        response.setUnpaidPercentageLoanDebt(account.getUnpaidPercentageLoanDebt());
        response.setCurrency(account.getCurrency());
        return response;
    }

    public Account updateAccount (Account account, UpdateAccountRequest request) {
           account.setLoanDebt(request.getLoanDebt());
           account.setPercentageDebt(request.getPercentageDebt());
           account.setStatus(request.getStatus());
           account.setBalance(request.getBalance());
           account.setClosingDate(request.getClosingDate());
           account.setUnpaidLoanDebt(request.getUnpaidLoanDebt());
           account.setUnpaidPercentageLoanDebt(request.getUnpaidPercentageLoanDebt());
           return account;
    }

    public UpdateAccountResponse convertTotUpdateResponse(Account account) {
        var response = new UpdateAccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        
        response.setLoanDebt(account.getLoanDebt());
        response.setPercentageDebt(account.getPercentageDebt());
        response.setStatus(account.getStatus());
        response.setBalance(account.getBalance());
        response.setClosingDate(account.getClosingDate());
        response.setUnpaidLoanDebt(account.getUnpaidLoanDebt());
        response.setUnpaidPercentageLoanDebt(account.getUnpaidPercentageLoanDebt());
        return response;
    }


}
