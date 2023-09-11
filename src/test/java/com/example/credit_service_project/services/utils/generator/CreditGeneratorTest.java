package com.example.credit_service_project.services.utils.generator;

import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreditGeneratorTest {

    @Test
    void getInterestRateByCountry() {
        BigDecimal expected = new BigDecimal("0.0");
        BigDecimal result = CreditGenerator.getInterestRateByCountry("Bulgaria");
        assertEquals(expected, result);
    }

    @Test
    void getInterestRateByCountryNotFoundException() {
        assertThrows(NotFoundException.class, () -> CreditGenerator.getInterestRateByCountry("S"));
    }

}