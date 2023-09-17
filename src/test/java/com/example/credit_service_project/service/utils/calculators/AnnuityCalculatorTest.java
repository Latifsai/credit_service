package com.example.credit_service_project.service.utils.calculators;

import com.example.credit_service_project.validation.exceptions.CalculationException;
import com.example.credit_service_project.validation.exceptions.CreditPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnuityCalculatorTest {

    @Test
    @DisplayName("Test calculate annuity method")
    void calculate() {
        BigDecimal[] expected = new BigDecimal[12];
        BigDecimal[] payments = new BigDecimal[12];

        BigDecimal monthlyInterestRate = BigDecimal.valueOf(5).divide(BigDecimal.valueOf(12 * 100), 10, HALF_UP);

        for (int i = 0; i < 12; i++) {
            expected[i] = BigDecimal.valueOf(85.61);
            payments[i] = AnnuityCalculator.calculate(BigDecimal.valueOf(1000), monthlyInterestRate, 12);
        }

        assertArrayEquals(expected, payments);
    }


    @Test
    @DisplayName("Test calculate annuity method throws the CalculationException")
    void calculateCalculationException() {
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(5).divide(BigDecimal.valueOf(12 * 100), 10, HALF_UP);

        assertThrows(CalculationException.class, () -> AnnuityCalculator.calculate(new BigDecimal("-100"),
                monthlyInterestRate, 12));
    }

    @Test
    @DisplayName("Test calculate annuity method throws the CreditPeriodException ")
    void calculateCreditPeriodException() {
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(5).divide(BigDecimal.valueOf(12 * 100), 10, HALF_UP);

        assertThrows(CreditPeriodException.class, () -> AnnuityCalculator.calculate(new BigDecimal("1000"),
                monthlyInterestRate, 6));
    }

}