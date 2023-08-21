package com.example.credit_service_project.service.utils.generator;

import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

public class AccountGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜẞabcdrfgilklmopqrstuvwxyzäöü0123456789";

    public static String createRandomAccountNumber(Integer length) {
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
