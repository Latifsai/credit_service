package com.example.credit_service_project.services.utils.generator;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CardGenerator {
    private static final String material = "0123456789";
    private static final String bankCode = "QWERTYUIOPASDFGHJKLMNBVCXZ";

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
        COUNTRY_IBAN_LENGTHS.put("Russia", 16);
        COUNTRY_IBAN_LENGTHS.put("Rusland", 16);
        COUNTRY_IBAN_LENGTHS.put("United States", 16);
        COUNTRY_IBAN_LENGTHS.put("USA", 16);
        COUNTRY_IBAN_LENGTHS.put("Israil", 21);
        COUNTRY_IBAN_LENGTHS.put("Poland", 26);
        COUNTRY_IBAN_LENGTHS.put("China", 12);
        COUNTRY_IBAN_LENGTHS.put("Turkey", 24);
        COUNTRY_IBAN_LENGTHS.put("Canada", 16);
        COUNTRY_IBAN_LENGTHS.put("UK", 14);
        COUNTRY_IBAN_LENGTHS.put("Italy", 20);
        COUNTRY_IBAN_LENGTHS.put("Romania", 16);
        COUNTRY_IBAN_LENGTHS.put("Brazil", 23);
        COUNTRY_IBAN_LENGTHS.put("Thailand", 16);
        COUNTRY_IBAN_LENGTHS.put("Norway", 13);
        COUNTRY_IBAN_LENGTHS.put("Japan", 16);


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
        COUNTRY_CODES.put("Israil", "IL");
        COUNTRY_CODES.put("Poland", "PL");
        COUNTRY_CODES.put("China", "");
        COUNTRY_CODES.put("Turkey", "TR");
        COUNTRY_CODES.put("Canada", "");
        COUNTRY_CODES.put("UK", "GB");
        COUNTRY_CODES.put("Italy", "IT");
        COUNTRY_CODES.put("Romania", "RO");
        COUNTRY_CODES.put("Brazil", "BR");
        COUNTRY_CODES.put("Thailand", "");
        COUNTRY_CODES.put("Norway", "NO");
        COUNTRY_CODES.put("Japan", "");
    }

    public static LocalDate setExpirationDate(LocalDate localDate, int years) {
        return localDate.plusYears(years);
    }

    public static String getIBAN(String country) {
        int length = COUNTRY_IBAN_LENGTHS.get(country);

        if (country.equals("UK") || country.equals("Romania") ) {
            return COUNTRY_CODES.get(country) + generateIBANAndCardCode(2) + generateBankCode(4) + generateIBANAndCardCode(length);
        }

        return COUNTRY_CODES.get(country) + generateIBANAndCardCode(length);
    }

    public static String generateIBANAndCardCode(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(material.length());
            stringBuilder.append(material.charAt(index));
        }
        return stringBuilder.toString();
    }

    private static String generateBankCode(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(bankCode.length());
            stringBuilder.append(bankCode.charAt(index));
        }
        return stringBuilder.toString();
    }
}
