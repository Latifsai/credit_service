package com.example.credit_service_project.generator;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

public class CardGenerator {
    private static final String material = "0123456789";
    private static final Integer numberLength = 12;
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
}
