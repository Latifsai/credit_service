package com.example.credit_service_project.service.utils.generator;

import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Calendar.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.*;

class CardGeneratorTest {

    @Test
    @DisplayName("Test setExpirationDate method")
    void setExpirationDate() {
        assertEquals(LocalDate.of(2024, SEPTEMBER, 11),
                CardGenerator.setExpirationDate(LocalDate.of(2023, SEPTEMBER, 11), 1));
    }

    @Test
    @DisplayName("Test getIBAN method")
    void getIBAN() {
        String result = CardGenerator.getIBAN("Germany");
        assertEquals("DE", result.substring(0, 2));
        assertEquals(22, result.length());
    }

    @Test
    @DisplayName("Test getIBANNotFoundException method")
    void getIBANNotFoundException() {
        assertThrows(NotFoundException.class, () -> CardGenerator.getIBAN("Nigera"));
    }

    @Test
    @DisplayName("Test generateCardDataNUMBERS method")
    void generateCardDataNUMBERS() {
        String result = CardGenerator.generateCardData(10, false);

        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(result);

        assertTrue(matcher.find());
        assertEquals(10,result.length());
    }

    @Test
    @DisplayName("Test generateCardDataCODE method")
    void generateCardDataCODE() {
        String result = CardGenerator.generateCardData(10, true);

        Pattern pattern = Pattern.compile("[A-Z]{10}");
        Matcher matcher = pattern.matcher(result);

        assertTrue(matcher.find());
        assertEquals(10,result.length());
    }
}