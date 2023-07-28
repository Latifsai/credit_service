package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.enums.AccountStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.Month.*;

public class EntityCreator {

    public static Account getAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(new BigDecimal("2500"));
        account.setPercentageDebt(new BigDecimal("250"));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("3000"));
        account.setOpeningDate(LocalDateTime.of(2020, APRIL, 21, 13,31,54));
        account.setClosingDate(LocalDateTime.of(2027, APRIL, 30, 13,31,54));
        account.setUnpaidLoanDebt(new BigDecimal("1300"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        account.setCurrency("$");
        return account;
    }

}
