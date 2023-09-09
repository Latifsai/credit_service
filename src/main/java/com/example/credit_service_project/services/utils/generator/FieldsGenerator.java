package com.example.credit_service_project.services.utils.generator;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

public class FieldsGenerator {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateRandomNumber(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public static LocalDate createOpeningDay() {
        return LocalDate.now();
    }

    public static LocalDate LocalDateCreateClosingDate(Integer yearsAmount) {
        return createOpeningDay().plusYears(yearsAmount);
    }
}
