package com.example.credit_service_project.services.utils.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DifferentiatedPaymentCalculator {

    public static BigDecimal calculateEMI(BigDecimal principal, BigDecimal monthlyInterestRate, int monthsTerm, int currentMonth) {
        BigDecimal principalPayment = principal.divide(BigDecimal.valueOf(monthsTerm), 2, RoundingMode.HALF_UP);
        BigDecimal remainingPrincipal = principal.subtract(principalPayment.multiply(BigDecimal.valueOf(currentMonth)));
        BigDecimal interestPayment = remainingPrincipal.multiply(monthlyInterestRate);
        BigDecimal payment = principalPayment.add(interestPayment);

        return payment.setScale(2, RoundingMode.HALF_UP);
    }


}
