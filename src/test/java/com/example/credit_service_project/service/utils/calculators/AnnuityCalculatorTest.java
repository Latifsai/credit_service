package com.example.credit_service_project.service.utils.calculators;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AnnuityCalculatorTest {

    @Test
    void calculate() {
        BigDecimal[] expected = new BigDecimal[12];
        BigDecimal[] payments = new BigDecimal[12];

        BigDecimal monthlyInterestRate = BigDecimal.valueOf(5).divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        for (int i = 0; i < 12; i++) {
            expected[i] = BigDecimal.valueOf(8.56);
            payments[i] = AnnuityCalculator.calculate(BigDecimal.valueOf(100), monthlyInterestRate, 12);
        }

        assertArrayEquals(expected, payments);

    }
}