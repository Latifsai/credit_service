package com.example.credit_service_project.service.utils.calculators;

import com.example.credit_service_project.validation.exceptions.CalculationException;
import com.example.credit_service_project.validation.exceptions.CreditPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferentiatedPaymentCalculatorTest {

    @Test
    @DisplayName("Test calculateEMI DifferentiatedPaymentCalculator method")
    void calculateEMI() {
        BigDecimal[] excepted = {
                BigDecimal.valueOf(91.66), BigDecimal.valueOf(90.97), BigDecimal.valueOf(90.27), BigDecimal.valueOf(89.58),
                BigDecimal.valueOf(88.89), BigDecimal.valueOf(88.19), new BigDecimal("87.50"), new  BigDecimal("86.80"),
                BigDecimal.valueOf(86.11), BigDecimal.valueOf(85.41), BigDecimal.valueOf(84.72), new BigDecimal("84.02")};

        BigDecimal[] payments = new BigDecimal[12];

        for (int i = 0; i < 12; i++) {
            payments[i] = DifferentiatedPaymentCalculator.calculateEMI(BigDecimal.valueOf(1000), BigDecimal.valueOf(0.1), 12, i);
        }

        assertArrayEquals(excepted, payments);
    }

    @Test
    @DisplayName("Test calculateEMI DifferentiatedPaymentCalculator method throws CalculationException")
    void calculateEMICalculationException() {
        assertThrows(CalculationException.class,() -> DifferentiatedPaymentCalculator.calculateEMI(new BigDecimal("-100"),
                BigDecimal.valueOf(0.1), 12, 1));
    }

    @Test
    @DisplayName("Test calculateEMI DifferentiatedPaymentCalculator method throws CreditPeriodException")
    void calculateEMICreditPeriodException() {
        assertThrows(CreditPeriodException.class,() -> DifferentiatedPaymentCalculator.calculateEMI(new BigDecimal("1000"),
                BigDecimal.valueOf(0.1), 20, 1));
    }

    @Test
    @DisplayName("Test calculateEMI DifferentiatedPaymentCalculator method throws CreditPeriodException more that normal")
    void calculateEMICreditPeriodExceptionMoreThatNormal() {
        assertThrows(CreditPeriodException.class,() -> DifferentiatedPaymentCalculator.calculateEMI(new BigDecimal("1000"),
                BigDecimal.valueOf(0.1), 150, 1));
    }

    @Test
    @DisplayName("Test calculateEMI DifferentiatedPaymentCalculator method throws CreditPeriodException less than normal")
    void calculateEMICreditPeriodExceptionLessThatNormal() {
        assertThrows(CreditPeriodException.class,() -> DifferentiatedPaymentCalculator.calculateEMI(new BigDecimal("1000"),
                BigDecimal.valueOf(0.1), 4, 1));
    }

}