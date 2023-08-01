package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidLoanDebt(new BigDecimal("1300"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        account.setCurrency("$");
        return account;
    }

    public static Account getAccountWithErrors() {
        Account account = new Account();
        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(new BigDecimal("-2500"));
        account.setPercentageDebt(new BigDecimal("-250"));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("3000"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidLoanDebt(new BigDecimal("1300"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        account.setCurrency("$");
        return account;
    }

    public static Account getAccountAfterOperation() {
        Account account = new Account();
        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(new BigDecimal("2500"));
        account.setPercentageDebt(new BigDecimal("250"));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("2900"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidLoanDebt(new BigDecimal("1300"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        account.setCurrency("$");
        return account;
    }

    public static Card getCard() {
        Card card = new Card();
        card.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        card.setCardNumber("A10B3U3OI9");
        card.setHolderName("Oleg Kirilov");
        card.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        card.setExpirationDate(LocalDate.of(2023, APRIL, 21));
        card.setBalance(new BigDecimal("3000"));
        card.setTransactionLimit(4);
        card.setDeliveryAddress("Wertach Strasse 34");
        card.setDigitalValet(true);
        card.setPaymentSystem(PaymentSystem.VISA);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setAccount(getAccount());
        return card;
    }

    public static Card getUpdatedCard() {
        Card card = new Card();
        card.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        card.setCardNumber("A10B3U3OI9");
        card.setHolderName("Oleg Kirilov");
        card.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        card.setExpirationDate(LocalDate.of(2023, APRIL, 21));
        card.setBalance(new BigDecimal("5000"));
        card.setTransactionLimit(4);
        card.setDeliveryAddress("Wertach Strasse 34");
        card.setDigitalValet(true);
        card.setPaymentSystem(PaymentSystem.VISA);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setAccount(getAccount());
        return card;
    }


    public static Operation getOperation() {
        Operation operation = new Operation();
        operation.setId(UUID.fromString("11117777-9999-1111-b491-426655440000"));
        operation.setSum(new BigDecimal("100"));
        operation.setType(OperationType.SPENDING);
        operation.setOperationEndMark(LocalDate.of(2023,8,1));
        operation.setOperationDetails("Bought a products in shop.");
        operation.setDebit(true);
        operation.setCurrency("$");
        operation.setAccount(getAccount());
        return operation;
    }
}
