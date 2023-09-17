package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.calculators.AnnuityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PaymentScheduleUtilTest {

    private Account account;
    private PaymentSchedule payment;

    @BeforeEach
    public void init() {
        account = EntityCreator.getAccount();
        payment = EntityCreator.getPayment();
    }

    @InjectMocks
    private PaymentScheduleUtil util;

    @Test
    @DisplayName("Test convertEntityToPaymentResponse method")
    void convertEntityToPaymentResponse() {
        assertNotNull(util.convertEntityToPaymentResponse(payment));
    }

    @Test
    @DisplayName("Test getNearestPaymentSchedule method")
    void getNearestPaymentSchedule() {
        payment.setPaymentDate(LocalDate.now());
        account.setPaymentSchedules(Collections.singletonList(payment));
        assertNotNull(util.getNearestPaymentSchedule(account));
    }

    @Test
    @DisplayName("Test convertToPayment method")
    void convertToPayment() {
        assertNotNull(util.convertToPayment(account));
    }

    @Test
    @DisplayName("Test calculatePaymentANNUITY method")
    void calculatePaymentANNUITY() {
        BigDecimal[] payments = new BigDecimal[12];

        int monthsAmount = 12;
        BigDecimal interestRate = new BigDecimal("5");
        BigDecimal creditSum = new BigDecimal("14580.65");
        Product product = EntityCreator.getProductUpdated();

        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        for (int i = 0; i < monthsAmount; i++) {
            payments[i] = AnnuityCalculator.calculate(creditSum, monthlyInterestRate, monthsAmount);
        }

        BigDecimal[] result = util.calculatePayment(monthsAmount, interestRate, creditSum, product);

        assertArrayEquals(payments, result);
    }

    @Test
    @DisplayName("Test calculatePaymentDIFFERENTIATED method")
    void calculatePaymentDIFFERENTIATED() {

        int monthsAmount = 12;
        BigDecimal interestRate = new BigDecimal("5");
        BigDecimal creditSum = new BigDecimal("14580.65");
        Product product = EntityCreator.getProduct();

        BigDecimal[] expected = {
                BigDecimal.valueOf(1219.69), BigDecimal.valueOf(1219.27), BigDecimal.valueOf(1218.85),
                BigDecimal.valueOf(1218.43), new BigDecimal("1218.00"), BigDecimal.valueOf(1217.58),
                BigDecimal.valueOf(1217.16), BigDecimal.valueOf(1216.74), BigDecimal.valueOf(1216.32),
                BigDecimal.valueOf(1215.89), BigDecimal.valueOf(1215.47), BigDecimal.valueOf(1215.05)
        };

        BigDecimal[] result = util.calculatePayment(monthsAmount, interestRate, creditSum, product);

        assertArrayEquals(expected, result);
    }
}