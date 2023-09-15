package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.utils.calculators.AnnuityCalculator;
import com.example.credit_service_project.service.utils.calculators.DifferentiatedPaymentCalculator;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;

import static com.example.credit_service_project.entity.enums.CalculationType.ANNUITY;
import static java.math.BigDecimal.ZERO;

@Service
public class PaymentScheduleUtil {
    public PaymentResponseDTO convertEntityToPaymentResponse(PaymentSchedule paymentSchedule) {
        return new PaymentResponseDTO(
                paymentSchedule.getId(),
                paymentSchedule.getPaymentDate(),
                paymentSchedule.getActualPaymentDate(),
                paymentSchedule.getSurcharge(),
                paymentSchedule.getMonthlyPayment(),
                paymentSchedule.isPaid()
        );
    }

    public PaymentSchedule getNearestPaymentSchedule(Account account) {
        return account.getPaymentSchedules().stream()
                .filter(t -> !t.isPaid())
                .filter(t -> t.getPaymentDate().isAfter(LocalDate.now().minusDays(1)))
                .min(Comparator.comparing(PaymentSchedule::getPaymentDate))
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_NEAREST_PAYMENT_MESSAGE));
    }


    public PaymentSchedule convertToPayment(Account account) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setAccount(account);
        paymentSchedule.setSurcharge(ZERO);
        paymentSchedule.setPaid(false);
        return paymentSchedule;
    }

    public BigDecimal[] calculatePayment(Integer monthsAmount, BigDecimal interestRate, BigDecimal creditSum, Product product) {
        BigDecimal[] payments = new BigDecimal[monthsAmount];
        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        if (product.getCalculationType().equals(ANNUITY)) {
            for (int month = 0; month < monthsAmount; month++) {
                payments[month] = AnnuityCalculator.calculate(creditSum, monthlyInterestRate, monthsAmount);
            }
        } else {
            for (int month = 0; month < monthsAmount; month++) {
                payments[month] = DifferentiatedPaymentCalculator.calculateEMI(creditSum, monthlyInterestRate,
                        monthsAmount, month + 1);
            }
        }
        return payments;
    }

}
