package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;
import static java.time.Month.APRIL;
import static java.time.Month.SEPTEMBER;

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

    public static PaymentSchedule getPayment() {
        PaymentSchedule payment = new PaymentSchedule();
        payment.setId(UUID.fromString("79a8b4d5-8e2c-4107-b171-f64b04e086dc"));
        payment.setActualPaymentDate(LocalDate.of(2023, SEPTEMBER, 18));
        payment.setActualPaymentDate(null);
        payment.setSurcharge(ZERO);
        payment.setMonthlyPayment(new BigDecimal("300.54"));
        payment.setPaid(false);
        return payment;
    }


    //credit
    public static Credit getCredit() {
        return new Credit(
                UUID.fromString("16bc026b-3a31-4927-b242-e5daabec82ad"),
                "consumer credit",
                new BigDecimal("14580.65"),
                12,
                BigDecimal.valueOf(5),
                ZERO,
                false,
                CreditStatus.ACTIVE,
                "USD",
                0,
                getAccount(),
                getAgreement(),
                getCreditOrder()
        );
    }

    //agreemnt
    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"));
        agreement.setNumber("123456789");
        agreement.setAgreementDate(LocalDate.of(2023, SEPTEMBER, 1));
        agreement.setTerminationDate(LocalDate.of(2024, SEPTEMBER, 1));
        agreement.setActive(true);
        agreement.setCreditOrderNumber("AAAAA");
        return agreement;
    }

    //prodcut
    public static Product getProduct() {
        return new Product(
                1L,
                "BMW X5",
                BigDecimal.valueOf(14580.65),
                false,
                true,
                false,
                "X5",
                "USD",
                CalculationType.DIFFERENTIATED
        );
    }

    public static CreditOrder getCreditOrder() {
        CreditOrder order = new CreditOrder();
        order.setId(UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"));
        order.setNumber("BBBBBB10");
        order.setAmount(BigDecimal.valueOf(14580.65));
        order.setCreationDate(LocalDate.of(2023, SEPTEMBER, 1));
        order.setLastUpdateDate(LocalDate.of(2023, SEPTEMBER, 1));
        order.setClientSalary(BigDecimal.valueOf(4000));
        order.setClientMonthlyExpenditure(BigDecimal.valueOf(2000));
        order.setPassiveIncome(ZERO);
        order.setMinPeriodMonths(12);
        order.setMaxPeriodMonths(120);
        order.setCreditOrderStatus(CreditOrderStatus.APPROVED);
        order.setClientEmail("john_manager@gmail.com");
        return order;
    }
}