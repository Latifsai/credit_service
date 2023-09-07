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

import static java.math.BigDecimal.ZERO;
import static java.time.Month.*;

public class EntityCreator {

    public static Account getAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(ZERO);
        account.setPercentageDebt(ZERO);
        account.setCountry("United States");
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("3000"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setLastUpdateDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidCreditSum(ZERO);
        account.setCurrency("USD");
        account.setUser(getUser());
        return account;
    }

    public static Account getAccountForValidation() {
        Account account = new Account();
        account.setId(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"));
        account.setAccountNumber(" ");
        account.setLoanDebt(ZERO);
        account.setPercentageDebt(ZERO);
        account.setCountry("United States");
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("-3000"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setLastUpdateDate(LocalDate.of(2020, APRIL, 21));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidCreditSum(ZERO);
        account.setCurrency(null);
        account.setUser(getUser());
        return account;
    }

    public static Account getUpgratedAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"));
        account.setAccountNumber("A10B3U3OI9");
        account.setLoanDebt(ZERO);
        account.setPercentageDebt(ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("4000"));
        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        account.setLastUpdateDate(LocalDate.of(2023, SEPTEMBER, 7));
        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
        account.setUnpaidCreditSum(ZERO);
        account.setCurrency("USD");
        return account;
    }
//
//    public static Account getAccountAfterOperation() {
//        Account account = new Account();
//        account.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
//        account.setAccountNumber("A10B3U3OI9");
//        account.setLoanDebt(new BigDecimal("2500"));
//        account.setPercentageDebt(new BigDecimal("250"));
//        account.setStatus(AccountStatus.ACTIVE);
//        account.setBalance(new BigDecimal("2900"));
//        account.setOpeningDate(LocalDate.of(2020, APRIL, 21));
//        account.setClosingDate(LocalDate.of(2027, APRIL, 30));
//        account.setUnpaidLoanDebt(new BigDecimal("1300"));
//        account.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
//        account.setCurrency("$");
//        account.setUser(getUser());
//        return account;
//    }

    public static Card getCard() {
        Card card = new Card();
        card.setId(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"));
        card.setCardNumber("A10B3U3OI9");
        card.setIBAN("0000 0000 0000 00");
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

    public static Card getCardForValidation() {
        Card card = new Card();
        card.setId(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"));
        card.setCardNumber("A10B3U3OI9");
        card.setIBAN(null);
        card.setHolderName("");
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
        card.setId(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"));
        card.setCardNumber("A10B3U3OI9");
        card.setIBAN("0000 0000 0000 00");
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

//    public static Card getCardAfterOperation() {
//        Card card = new Card();
//        card.setId(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"));
//        card.setCardNumber("A10B3U3OI9");
//        card.setHolderName("Oleg Kirilov");
//        card.setOpeningDate(LocalDate.of(2020, APRIL, 21));
//        card.setExpirationDate(LocalDate.of(2023, APRIL, 21));
//        card.setBalance(new BigDecimal("2900"));
//        card.setDeliveryAddress("Wertach Strasse 34");
//        card.setDigitalValet(true);
//        card.setPaymentSystem(PaymentSystem.VISA);
//        card.setCardStatus(CardStatus.ACTIVE);
//        card.setAccount(getAccount());
//        return card;
//    }
//
//    public static Operation getOperation() {
//        Operation operation = new Operation();
//        operation.setId(UUID.fromString("11117777-9999-1111-b491-426655440000"));
//        operation.setSum(new BigDecimal("100"));
//        operation.setType(OperationType.SPENDING);
//        operation.setOperationEndMark(LocalDateTime.of(2023, 8, 1, 17, 32, 49));
//        operation.setOperationDetails("Bought a products in shop.");
//        operation.setDebit(true);
//        operation.setCurrency("$");
//        operation.setAccount(getAccount());
//        return operation;
//    }
//
//    public static Operation getUpdatedOperation() {
//        Operation operation = new Operation();
//        operation.setId(UUID.fromString("11117777-9999-1111-b491-426655440000"));
//        operation.setSum(new BigDecimal("100"));
//        operation.setType(OperationType.MONTHLY_PAYMENT);
//        operation.setOperationEndMark(LocalDateTime.of(2023, 8, 1, 17, 32, 49));
//        operation.setOperationDetails("Mortgage payment");
//        operation.setDebit(true);
//        operation.setCurrency("$");
//        operation.setAccount(getAccount());
//        return operation;
//    }

    public static Role getManagerRole() {
        return new Role(
                2,
                "ROLE_MANAGER"
        );
    }

    public static Role getClientRole() {
        return new Role(
                1,
                "ROLE_CLIENT"
        );
    }

    public static User getUser() {
        User user = new User();
        user.setId(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"));
        user.setName("Lew");
        user.setSurname("Kim");
        user.setPassword("$2a$10$ruSCjhXF8i30nqNtvAeS0OSyzfF3vOR3Oiq6keZN.XLRgRw9ZvPCC");
        user.setSalary(new BigDecimal("2500"));
        user.setPassiveIncome(ZERO);
        user.setExpenses(new BigDecimal("1500"));
        user.setAddress("Johan's Str 34");
        user.setEmail("john_manager@loewen.de");
        user.setPhone("+49 176 28835002");
        user.setRegistrationDate(LocalDate.of(2021, 3, 14));
        user.setUpdateDate(LocalDate.of(2021, 3, 14));
        user.setRole(getManagerRole());
        return user;
    }

    public static User getUserForValidation() {
        User user = new User();
        user.setId(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"));
        user.setName("  ");
        user.setSurname("Kim");
        user.setPassword("$2a$10$ruSCjhXF8i30nqNtvAeS0OSyzfF3vOR3Oiq6keZN.XLRgRw9ZvPCC");
        user.setSalary(new BigDecimal("2500"));
        user.setPassiveIncome(ZERO);
        user.setExpenses(new BigDecimal("1500"));
        user.setAddress(null);
        user.setEmail("john_manager@loewen.de");
        user.setPhone("");
        user.setRegistrationDate(LocalDate.of(2021, 3, 14));
        user.setUpdateDate(LocalDate.of(2021, 3, 14));
        user.setRole(getManagerRole());
        return user;
    }

    public static User getUpdatedUser() {
        User user = new User();
        user.setId(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"));
        user.setName("Lew");
        user.setSurname("Kim");
        user.setPassword("$2a$10$ruSCjhXF8i30nqNtvAeS0OSyzfF3vOR3Oiq6keZN.XLRgRw9ZvPCC");
        user.setSalary(new BigDecimal("4000"));
        user.setPassiveIncome(ZERO);
        user.setExpenses(new BigDecimal("2000"));
        user.setAddress("Johan's Str 34");
        user.setEmail("john_manager@loewen.de");
        user.setPhone("+49 176 28835002");
        user.setRegistrationDate(LocalDate.of(2021, 3, 14));
        user.setUpdateDate(LocalDate.of(2023, 9, 7));
        return user;
    }


//    public static PaymentSchedule getPayment() {
//        return new PaymentSchedule(
//                UUID.fromString("22228888-4444-9999-a456-426655440000"),
//                LocalDate.of(2023, AUGUST, 30),
//                null,
//                new BigDecimal("0"),
//                new BigDecimal("300"),
//                new BigDecimal("57"),
//                false,
//                getAccount()
//        );
//
//    }
//
}
