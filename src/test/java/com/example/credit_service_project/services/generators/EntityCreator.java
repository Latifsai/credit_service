package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidLoanDebt(new BigDecimal("1300"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        account.setCurrency("$");
        account.setClient(getClient());
        return account;
    }

    public static Account getUpgratedAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(new BigDecimal("2500"));
        account.setPercentageDebt(new BigDecimal("250"));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("5000"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidLoanDebt(new BigDecimal("2000"));
        account.setUnpaidPercentageLoanDebt(new BigDecimal("200"));
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
        account.setClient(getClient());
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
        card.setDeliveryAddress("Wertach Strasse 34");
        card.setDigitalValet(true);
        card.setPaymentSystem(PaymentSystem.VISA);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setAccount(getAccount());
        return card;
    }

    public static Card getCardAfterOperation() {
        Card card = new Card();
        card.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        card.setCardNumber("A10B3U3OI9");
        card.setHolderName("Oleg Kirilov");
        card.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        card.setExpirationDate(LocalDate.of(2023, APRIL, 21));
        card.setBalance(new BigDecimal("2900"));
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
        operation.setOperationEndMark(LocalDateTime.of(2023, 8, 1, 17, 32, 49));
        operation.setOperationDetails("Bought a products in shop.");
        operation.setDebit(true);
        operation.setCurrency("$");
        operation.setAccount(getAccount());
        return operation;
    }

    public static Operation getUpdatedOperation() {
        Operation operation = new Operation();
        operation.setId(UUID.fromString("11117777-9999-1111-b491-426655440000"));
        operation.setSum(new BigDecimal("100"));
        operation.setType(OperationType.MONTHLY_PAYMENT);
        operation.setOperationEndMark(LocalDateTime.of(2023, 8, 1, 17, 32, 49));
        operation.setOperationDetails("Mortgage payment");
        operation.setDebit(true);
        operation.setCurrency("$");
        operation.setAccount(getAccount());
        return operation;
    }

    public static Manager getManager() {
        var manager = new Manager();
        manager.setId(UUID.fromString("55553333-0000-4444-b491-426655440000"));
        manager.setName("Ivan");
        manager.setSurname("Semyonov");
        manager.setSurname("ivan_manager@loewen.de");
        return manager;
    }

    public static Manager getUpdatedManager() {
        var manager = new Manager();
        manager.setId(UUID.fromString("55553333-0000-4444-b491-426655440000"));
        manager.setName("Ivan");
        manager.setSurname("Simonov");
        manager.setSurname("main_manager@loewen.de");
        return manager;
    }

    public static Client getClient() {
        Client client = new Client();
        client.setId(UUID.fromString("88882222-6666-4444-b491-426655440000"));
        client.setName("Lew");
        client.setSurname("Kim");
        client.setIncome(new BigDecimal("2500"));
        client.setExpenses(new BigDecimal("1500"));
        client.setManager(getManager());
        return client;
    }

    public static Client getUpdatedClient() {
        Client client = new Client();
        client.setId(UUID.fromString("88882222-6666-4444-b491-426655440000"));
        client.setName("Lew");
        client.setSurname("Kim");
        client.setIncome(new BigDecimal("3500"));
        client.setExpenses(new BigDecimal("2000"));
        client.setManager(getManager());
        return client;
    }


    public static PaymentSchedule getPayment() {
        return new PaymentSchedule(
                UUID.fromString("22228888-4444-9999-a456-426655440000"),
                LocalDate.of(2023, AUGUST, 30),
                null,
                new BigDecimal("0"),
                new BigDecimal("300"),
                new BigDecimal("57"),
                false,
                getAccount()
                );

    }
}
