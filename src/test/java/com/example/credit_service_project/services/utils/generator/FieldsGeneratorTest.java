package com.example.credit_service_project.services.utils.generator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldsGeneratorTest {
    private final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Test
    void generateRandomNumber() {
        String actual = FieldsGenerator.generateRandomNumber(10);
        assertEquals(10, actual.length());
        for (char c: actual.toCharArray()) {
            assertTrue(CHARACTERS.indexOf(c) >= 0);

        }
    }

    @Test
    void createOpeningDay() {
        assertEquals(LocalDate.now(), FieldsGenerator.createOpeningDay());
    }

    @Test
    void localDateCreateClosingDate() {
        assertEquals(LocalDate.of(2024, Month.SEPTEMBER, 9), FieldsGenerator.LocalDateCreateClosingDate(1));
    }
}