package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.dto.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.service.utils.generator.FieldsGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class AccountUtil {

    public Account convertAddRequestToAccount(CreateAccountRequestDTO request, User user) {
        Account account = new Account();
        account.setUser(user);
        account.setCountry(request.getCountry());
        account.setAccountNumber(FieldsGenerator.generateRandomNumber(request.getAccountNumberLength()));
        account.setLoanDebt(BigDecimal.ZERO);
        account.setPercentageDebt(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(request.getBalance());
        account.setOpeningDate(FieldsGenerator.createOpeningDay());
        account.setClosingDate(FieldsGenerator.LocalDateCreateClosingDate(request.getYearsAmountForClosingDate()));
        account.setUnpaidCreditSum(BigDecimal.ZERO);
        account.setCurrency(request.getCurrency());
        account.setLastUpdateDate(LocalDate.now());
        return account;
    }

    public AccountResponseDTO convertAccountToAddResponse(Account account) {
        return AccountResponseDTO.builder()
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
