package com.example.credit_service_project.services.utils.generator;

import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Calendar.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.*;

class CardGeneratorTest {

    @Test
    void setExpirationDate() {
        assertEquals(LocalDate.of(2024, SEPTEMBER, 11),
                CardGenerator.setExpirationDate(LocalDate.of(2023, SEPTEMBER, 11), 1));
    }

    @Test
    void getIBAN() {
        String result = CardGenerator.getIBAN("Germany");
        assertEquals("DE", result.substring(0, 2));
        assertEquals(22, result.length());
    }

    @Test
    void getIBANNotFoundException() {
        assertThrows(NotFoundException.class, () -> CardGenerator.getIBAN("Nigera"));
    }

    @Test
    void generateCardDataNUMBERS() {
        String result = CardGenerator.generateCardData(10, false);

        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(result);

        assertTrue(matcher.find());
        assertEquals(10,result.length());
    }

    @Test
    void generateCardDataCODE() {
        String result = CardGenerator.generateCardData(10, true);

        Pattern pattern = Pattern.compile("[A-Z]{10}");
        Matcher matcher = pattern.matcher(result);

        assertTrue(matcher.find());
        assertEquals(10,result.length());
    }
}