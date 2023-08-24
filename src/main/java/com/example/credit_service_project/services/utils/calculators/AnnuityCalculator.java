package com.example.credit_service_project.services.utils.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnnuityCalculator {
    public static BigDecimal calculate(BigDecimal principal, BigDecimal monthlyInterestRate, int mountsAmount) {
        BigDecimal numerator = monthlyInterestRate.multiply((BigDecimal.ONE.add(monthlyInterestRate)).pow(mountsAmount));
        BigDecimal denominator = (BigDecimal.ONE.add(monthlyInterestRate).pow(mountsAmount)).subtract(BigDecimal.ONE);
        BigDecimal emi = principal.multiply(numerator.divide(denominator, 10, RoundingMode.HALF_UP));
        return emi.setScale(2, RoundingMode.HALF_UP);
    }
}
