package com.example.credit_service_project.service.utils.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldsGeneratorTest {
    private final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Test
    @DisplayName("Test generateRandomNumber method")
    void generateRandomNumber() {
        String actual = FieldsGenerator.generateRandomNumber(10);
        assertEquals(10, actual.length());
        for (char c: actual.toCharArray()) {
            assertTrue(CHARACTERS.indexOf(c) >= 0);

        }
    }

    @Test
    @DisplayName("Test createOpeningDay method")
    void createOpeningDay() {
        assertEquals(LocalDate.now(), FieldsGenerator.createOpeningDay());
    }

    @Test
    @DisplayName("Test localDateCreateClosingDate method")
    void localDateCreateClosingDate() {
        assertEquals(LocalDate.of(2024, Month.SEPTEMBER, 17), FieldsGenerator.LocalDateCreateClosingDate(1));
    }
}