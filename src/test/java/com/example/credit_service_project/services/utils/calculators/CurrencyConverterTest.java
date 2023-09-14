package com.example.credit_service_project.services.utils.calculators;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void convertCurrencyIfSameCode() {
        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();

        BigDecimal actual = CurrencyConverter.convertCurrency(product, account);
        assertEquals(BigDecimal.valueOf(14580.65), actual);
    }
    @Test
    void convertCurrencyIfDifferentCode() {
        Product product = EntityCreator.getProduct();
        product.setCurrencyCode("EUR");
        Account account = EntityCreator.getAccount();

        BigDecimal actual = CurrencyConverter.convertCurrency(product, account);
        assertEquals(BigDecimal.valueOf(15636.29), actual);
    }

    @Test
    void convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_TARGET_CURRENCY() {
        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();
        account.setCurrency("RUB");

        assertThrows(OperationException.class,() -> CurrencyConverter.convertCurrency(product, account));
    }

    @Test
    void convertCurrencyIfDifferentCodeOperationExceptionUNKNOWN_SOURCE_CURRENCY() {
        Product product = EntityCreator.getProduct();
        product.setCurrencyCode("RUB");
        Account account = EntityCreator.getAccount();

        assertThrows(OperationException.class,() -> CurrencyConverter.convertCurrency(product, account));
    }

}