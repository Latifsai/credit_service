package com.example.credit_service_project.service.utils.calculators;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DifferentiatedPaymentCalculatorTest {

    @Test
    void calculateEMI() {

        BigDecimal[] excepted = {
                BigDecimal.valueOf(9.16), BigDecimal.valueOf(9.09), BigDecimal.valueOf(9.02), BigDecimal.valueOf(8.95),
                BigDecimal.valueOf(8.89), BigDecimal.valueOf(8.82), BigDecimal.valueOf(8.75), BigDecimal.valueOf(8.68),
                BigDecimal.valueOf(8.61), BigDecimal.valueOf(8.54), BigDecimal.valueOf(8.47), new BigDecimal("8.40")};

        BigDecimal[] payments = new BigDecimal[12];

        for (int i = 0; i < 12; i++) {
            payments[i] = DifferentiatedPaymentCalculator.calculateEMI(BigDecimal.valueOf(100), BigDecimal.valueOf(0.1), 12, i);
        }

        assertArrayEquals(excepted, payments);
    }
}