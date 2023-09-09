package com.example.credit_service_project.services.utils.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DifferentiatedPaymentCalculator {

    public static BigDecimal calculateEMI(BigDecimal loanAmount, BigDecimal monthlyInterestRate, int loanTermMonths, int currentMonth) {
        BigDecimal basicallyPayment = calculateMonthlyPayment(loanAmount, loanTermMonths);
        BigDecimal interestPayment = calculateInterestPayment(loanAmount, basicallyPayment, currentMonth, monthlyInterestRate);

        BigDecimal payment = basicallyPayment.add(interestPayment);

        return payment.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, int loanTermMonths) {
        return loanAmount.divide(BigDecimal.valueOf(loanTermMonths), 10, RoundingMode.HALF_UP);
    }
    private static BigDecimal calculateInterestPayment(BigDecimal loanAmount, BigDecimal basicallyPayment,int currentMonth,BigDecimal interestRate) {

        return (loanAmount.subtract((basicallyPayment.multiply(BigDecimal.valueOf(currentMonth)))))
                .multiply((interestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);
    }
}
