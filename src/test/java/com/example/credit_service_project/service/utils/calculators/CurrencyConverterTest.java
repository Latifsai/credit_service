package com.example.credit_service_project.service.utils.calculators;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    private final Product product = EntityCreator.getProduct();
    private final Account account = EntityCreator.getAccount();

    @Test
    @DisplayName("Test convertCurrencyIfSameCurrencyCode method")
    void convertCurrencyIfSameCode() {
        BigDecimal actual = CurrencyConverter.convertCurrency(product, account);
        assertEquals(BigDecimal.valueOf(14580.65), actual);
    }

    @Test
    @DisplayName("Test convertCurrencyIfDifferentCurrencyCode method")
    void convertCurrencyIfDifferentCode() {
        product.setCurrencyCode("EUR");

        BigDecimal actual = CurrencyConverter.convertCurrency(product, account);
        assertEquals(BigDecimal.valueOf(15620.25), actual);
    }

    @Test
    @DisplayName("Test convertCurrencyIfDifferentCurrencyCode_2 method")
    void convertCurrencyIfDifferentCode_2() {
        product.setCurrencyCode("CAD");
        account.setCurrency("THB");

        BigDecimal actual = CurrencyConverter.convertCurrency(product, account);
        assertEquals(new BigDecimal("390481.03"), actual);
    }


    @Test
    @DisplayName("Test convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_TARGET_CURRENCY method")
    void convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_TARGET_CURRENCY() {
        account.setCurrency("RUB");
        assertThrows(OperationException.class, () -> CurrencyConverter.convertCurrency(product, account));
    }

    @Test
    @DisplayName("Test convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_SOURCE_CURRENCY method")
    void convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_SOURCE_CURRENCY() {
        product.setCurrencyCode("RUB");
        assertThrows(OperationException.class, () -> CurrencyConverter.convertCurrency(product, account));
    }

}