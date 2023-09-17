package com.example.credit_service_project.service.utils.calculators;

import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.CalculationException;
import com.example.credit_service_project.validation.exceptions.CreditPeriodException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnnuityCalculator {
    public static BigDecimal calculate(BigDecimal principal, BigDecimal monthlyInterestRate, int mountsAmount) {

        parametersValidation(principal, mountsAmount);

        BigDecimal numerator = monthlyInterestRate.multiply((BigDecimal.ONE.add(monthlyInterestRate)).pow(mountsAmount));
        BigDecimal denominator = (BigDecimal.ONE.add(monthlyInterestRate).pow(mountsAmount)).subtract(BigDecimal.ONE);
        BigDecimal emi = principal.multiply(numerator.divide(denominator, 10, RoundingMode.HALF_UP));
        return emi.setScale(2, RoundingMode.HALF_UP);
    }

    private static void parametersValidation(BigDecimal principal, int months) {
        if (principal.compareTo(BigDecimal.valueOf(800)) < 0) {
            throw new CalculationException(ErrorsMessage.INAPPROPRIATE_PRINCIPAL_MESSAGE);
        }

        if ((months < 12 || months > 120) && months % 12 != 0) {
            throw new CreditPeriodException(ErrorsMessage.INAPPROPRIATE_NUMBER_OF_MONTHS_MESSAGE);
        }
    }

}
