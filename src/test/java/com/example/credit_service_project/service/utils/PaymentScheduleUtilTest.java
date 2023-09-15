package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.calculators.AnnuityCalculator;
import com.example.credit_service_project.service.utils.calculators.DifferentiatedPaymentCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentScheduleUtilTest {

    private Account account;
    private PaymentSchedule payment;

    @BeforeEach
    public void init() {
        account = EntityCreator.getAccount();
        payment = EntityCreator.getPayment();
    }

    @Mock
    private PaymentScheduleUtil util;

    @Test
    void convertEntityToPaymentResponse() {
        PaymentResponseDTO response = PaymentDTOGenerator.getPaymentResponseDTO();

        when(util.convertEntityToPaymentResponse(payment)).thenReturn(response);
        assertEquals(response, util.convertEntityToPaymentResponse(payment));
    }

    @Test
    void getNearestPaymentSchedule() {
        when(util.getNearestPaymentSchedule(account)).thenReturn(payment);
        assertEquals(payment, util.getNearestPaymentSchedule(account));
    }

    @Test
    void convertToPayment() {
        when(util.convertToPayment(account)).thenReturn(payment);
        assertEquals(payment, util.convertToPayment(account));
    }

    @Test
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

        when(util.calculatePayment(monthsAmount, interestRate, creditSum, product)).thenReturn(payments);

        BigDecimal[] result = util.calculatePayment(monthsAmount, interestRate, creditSum, product);

        assertArrayEquals(payments, result);
    }

    @Test
    void calculatePaymentDIFFERENTIATED() {
        BigDecimal[] payments = new BigDecimal[12];

        int monthsAmount = 12;
        BigDecimal interestRate = new BigDecimal("5");
        BigDecimal creditSum = new BigDecimal("14580.65");
        Product product = EntityCreator.getProduct();

        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        for (int i = 0; i < monthsAmount; i++) {
            payments[i] = DifferentiatedPaymentCalculator.calculateEMI(creditSum, monthlyInterestRate, monthsAmount, i);
        }

        when(util.calculatePayment(monthsAmount, interestRate, creditSum, product)).thenReturn(payments);

        BigDecimal[] result = util.calculatePayment(monthsAmount, interestRate, creditSum, product);

        assertArrayEquals(payments, result);
    }
}