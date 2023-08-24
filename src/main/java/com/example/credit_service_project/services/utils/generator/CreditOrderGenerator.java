package com.example.credit_service_project.services.utils.generator;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Map;
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

    public static BigDecimal convertCurrency(Product product, Account account) {
        Map<String, Double> exchangeRates = EURToAnyGenerator.generatCurrencyMap();
        BigDecimal sum = product.getSum();
        String productCurrency = product.getCurrencyCode();
        String accountCurrency = account.getCurrency();

        if (productCurrency.equals(accountCurrency)) {
            return sum;
        }

        if (exchangeRates.containsKey(productCurrency)) {
            BigDecimal exchangeRate = BigDecimal.valueOf(exchangeRates.get(productCurrency));

            if (accountCurrency.equals("EUR")) {
                return sum.multiply(exchangeRate);
            } else if (exchangeRates.containsKey(accountCurrency)) {
                BigDecimal targetExchangeRate = BigDecimal.valueOf(exchangeRates.get(accountCurrency));
                BigDecimal convertedSum = sum.multiply(targetExchangeRate).divide(exchangeRate, 2, RoundingMode.HALF_UP);
                return convertedSum;
            } else {
                throw new OperationException(ErrorsMessage.UNKNOWN_TARGET_CURRENCY);
            }
        } else {
            throw new OperationException(ErrorsMessage.UNKNOWN_SOURCE_CURRENCY);
        }
    }



}
