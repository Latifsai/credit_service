package com.example.credit_service_project.service.utils.calculators;

import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.CalculationException;
import com.example.credit_service_project.validation.exceptions.CreditPeriodException;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class DifferentiatedPaymentCalculator {

    public static BigDecimal calculateEMI(BigDecimal loanAmount, BigDecimal monthlyInterestRate, int loanTermMonths, int currentMonth) {
        parametersValidation(loanAmount, loanTermMonths);

        BigDecimal basicallyPayment = calculateMonthlyPayment(loanAmount, loanTermMonths);
        BigDecimal interestPayment = calculateInterestPayment(loanAmount, basicallyPayment, currentMonth, monthlyInterestRate);

        BigDecimal payment = basicallyPayment.add(interestPayment);

        return payment.setScale(2, HALF_UP);
    }

    private static BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, int loanTermMonths) {
        return loanAmount.divide(BigDecimal.valueOf(loanTermMonths), 10, HALF_UP);
    }

    private static BigDecimal calculateInterestPayment(BigDecimal loanAmount, BigDecimal basicallyPayment, int currentMonth, BigDecimal interestRate) {

        return (loanAmount.subtract((basicallyPayment.multiply(BigDecimal.valueOf(currentMonth)))))
                .multiply((interestRate.divide(BigDecimal.valueOf(12), 10, HALF_UP))).setScale(2, HALF_UP);
    }

    private static void parametersValidation(BigDecimal principal, int months) {
        if (principal.compareTo(BigDecimal.valueOf(800)) < 0) {
            throw new CalculationException(ErrorsMessage.INAPPROPRIATE_PRINCIPAL_MESSAGE);
        }

        if (months % 12 != 0 || (months < 12 || months > 120)) {
            throw new CreditPeriodException(ErrorsMessage.INAPPROPRIATE_NUMBER_OF_MONTHS_MESSAGE);
        }
    }
}
