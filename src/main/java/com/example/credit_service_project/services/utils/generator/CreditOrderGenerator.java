package com.example.credit_service_project.services.utils.generator;

import java.security.SecureRandom;
import java.util.Random;

public class CreditOrderGenerator {


    public static Integer minPeriod = 12;
    public static Integer maxPeriod = 120;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ0123456789";

    public static String createCreditOrderNumber(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }





}
