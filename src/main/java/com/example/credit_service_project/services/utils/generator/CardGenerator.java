package com.example.credit_service_project.services.utils.generator;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CardGenerator {
    private static final String material = "0123456789";
    private static final Integer numberLength = 12;

    private static final Map<String, Integer> COUNTRY_IBAN_LENGTHS = new HashMap<>();
    private static final Map<String, String> COUNTRY_CODES = new HashMap<>();

    static {
        COUNTRY_IBAN_LENGTHS.put("Germany", 20);
        COUNTRY_IBAN_LENGTHS.put("Deutschland", 20);
        COUNTRY_IBAN_LENGTHS.put("Austria", 18);
        COUNTRY_IBAN_LENGTHS.put("Österreich", 18);
        COUNTRY_IBAN_LENGTHS.put("Albania", 26);
        COUNTRY_IBAN_LENGTHS.put("Albanien", 26);
        COUNTRY_IBAN_LENGTHS.put("Greece", 25);
        COUNTRY_IBAN_LENGTHS.put("Griechenland", 25);
        COUNTRY_IBAN_LENGTHS.put("France", 25);
        COUNTRY_IBAN_LENGTHS.put("Frankreich", 25);
        COUNTRY_IBAN_LENGTHS.put("Ukraine", 27);
        COUNTRY_IBAN_LENGTHS.put("Russia", 12);
        COUNTRY_IBAN_LENGTHS.put("Rusland", 12);
        COUNTRY_IBAN_LENGTHS.put("United States", 12);
        COUNTRY_IBAN_LENGTHS.put("USA", 12);
        COUNTRY_IBAN_LENGTHS.put("Israel", 21);
        COUNTRY_IBAN_LENGTHS.put("Poland", 26);
        COUNTRY_IBAN_LENGTHS.put("China", 12);

        COUNTRY_CODES.put("Germany", "DE");
        COUNTRY_CODES.put("Deutschland", "DE");
        COUNTRY_CODES.put("Austria", "AT");
        COUNTRY_CODES.put("Österreich", "AT");
        COUNTRY_CODES.put("Albania", "AL");
        COUNTRY_CODES.put("Albanien", "AL");
        COUNTRY_CODES.put("Greece", "GR");
        COUNTRY_CODES.put("Griechenland", "GR");
        COUNTRY_CODES.put("France", "FR");
        COUNTRY_CODES.put("Frankreich", "FR");
        COUNTRY_CODES.put("Ukraine", "UA");
        COUNTRY_CODES.put("Russia", "RU");
        COUNTRY_CODES.put("Rusland", "");
        COUNTRY_CODES.put("USA", "");
        COUNTRY_CODES.put("United States", "");
        COUNTRY_CODES.put("Israel", "IL");
        COUNTRY_CODES.put("Poland", "PL");
        COUNTRY_CODES.put("China", "");
    }

    public static String generateCardNumber() {
        StringBuilder stringBuilder = new StringBuilder(numberLength);
        Random random = new SecureRandom();
        for (int i = 0; i < numberLength; i++) {
            int index = random.nextInt(material.length());
            stringBuilder.append(material.charAt(index));
        }
        return stringBuilder.toString();
    }

    public static LocalDate setExpirationDate(LocalDate localDate, int years) {
        return localDate.plusYears(years);
    }

    public static String getIBAN(String country) {
        int length = COUNTRY_IBAN_LENGTHS.get(country);
        return COUNTRY_CODES.get(country) + generateIBAN(length);
    }

    private static String generateIBAN(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(material.length());
            stringBuilder.append(material.charAt(index));
        }
        return stringBuilder.toString();
    }
}
