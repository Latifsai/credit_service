package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTOResponse;
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


}
