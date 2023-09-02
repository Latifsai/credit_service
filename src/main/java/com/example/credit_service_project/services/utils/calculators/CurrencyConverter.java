package com.example.credit_service_project.services.utils.calculators;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.generator.EURToAnyGenerator;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static java.math.RoundingMode.HALF_UP;

public class CurrencyConverter {
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

            if (accountCurrency.equals("EUR") ) {
                return sum.divide(exchangeRate,2, HALF_UP); // 1 000 JPY * 150
            } else if (exchangeRates.containsKey(accountCurrency)) {
                BigDecimal targetExchangeRate = BigDecimal.valueOf(exchangeRates.get(accountCurrency));
                BigDecimal convertedSum = sum.multiply(targetExchangeRate).divide(exchangeRate, 2, HALF_UP);
                return convertedSum;
            } else {
                throw new OperationException(ErrorsMessage.UNKNOWN_TARGET_CURRENCY);
            }
        } else {
            throw new OperationException(ErrorsMessage.UNKNOWN_SOURCE_CURRENCY);
        }
    }
}
