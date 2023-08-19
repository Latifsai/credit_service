package com.example.credit_service_project.service.utils.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DifferentiatedPaymentCalculator {

    public static BigDecimal calculateEMI(BigDecimal principal, BigDecimal interestRate, int monthsTerm, int currentMonth) {

        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        BigDecimal numerator = principal.subtract(BigDecimal.valueOf(currentMonth - 1).multiply(principal).divide(BigDecimal.valueOf(monthsTerm), 10, RoundingMode.HALF_UP));

        BigDecimal emi = principal.divide(BigDecimal.valueOf(monthsTerm), 10, RoundingMode.HALF_UP).add(numerator.multiply(monthlyInterestRate));
        return emi.setScale(2, RoundingMode.HALF_UP);
    }


}
