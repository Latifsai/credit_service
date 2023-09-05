package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.CreateAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.services.utils.generator.AccountGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AccountUtil {

    public Account convertAddRequestToAccount(CreateAccountDTORequest request, User user) {
        Account account = new Account();
        account.setUser(user);
        account.setCountry(request.getCountry());
        account.setAccountNumber(AccountGenerator.createRandomAccountNumber(request.getAccountNumberLength()));
        account.setLoanDebt(BigDecimal.ZERO);
        account.setPercentageDebt(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(request.getBalance());
        account.setOpeningDate(AccountGenerator.createOpeningDay());
        account.setClosingDate(AccountGenerator.LocalDateCreateClosingDate(request.getYearsAmountForClosingDate()));
        account.setUnpaidCreditSum(BigDecimal.ZERO);
        account.setCurrency(request.getCurrency());
        account.setLastUpdateDate(LocalDate.now());
        return account;
    }

    public AccountDTOResponse convertAccountToAddResponse(Account account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .clientInitial(account.getUser().getName() + " " + account.getUser().getSurname())
                .loanDebt(account.getLoanDebt())
                .percentageDebt(account.getPercentageDebt())
                .status(account.getStatus())
                .balance(account.getBalance())
                .closingDate(account.getClosingDate())
                .unpaidCreditSum(account.getUnpaidCreditSum())
                .currency(account.getCurrency())
                .country(account.getCountry())
                .build();
    }


    public Account updateAccount(Account account, UpdateAccountRequest request) {
        if (request.getLoanDebt() != null) account.setLoanDebt(request.getLoanDebt());
        if (request.getPercentageDebt() != null) account.setPercentageDebt(request.getPercentageDebt());
        if (request.getStatus() != null) account.setStatus(request.getStatus());
        if (request.getBalance() != null) account.setBalance(request.getBalance());
        if (request.getUnpaidCreditSum() != null) account.setUnpaidCreditSum(request.getUnpaidCreditSum());
        if (request.getCountry() != null && !request.getCountry().trim().isEmpty()) account.setCountry(request.getCountry());
        account.setLastUpdateDate(LocalDate.now());
        return account;
    }

}
