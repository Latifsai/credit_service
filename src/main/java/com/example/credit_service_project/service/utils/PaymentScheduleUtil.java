package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
import com.example.credit_service_project.DTO.paymentDTO.AddPaymentScheduleDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.utils.calculators.AnnuityCalculator;
import com.example.credit_service_project.service.utils.calculators.DifferentiatedPaymentCalculator;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NearestPaymentNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;

import static com.example.credit_service_project.entity.enums.CalculationType.ANNUITY;
import static java.math.BigDecimal.ZERO;

@Service
public class PaymentScheduleUtil {

    public PaymentSchedule convertPaymentDTORequestToPayment(AddPaymentRequestDTO request, Account account) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setAccount(account);

        paymentSchedule.setPaymentDate(request.getPaymentDate());
        paymentSchedule.setSurcharge(ZERO);
        paymentSchedule.setMonthlyPayment(request.getMonthlyPayment());
        paymentSchedule.setPaid(false);
        return paymentSchedule;
    }

    public AddPaymentScheduleDTOResponse convertEntityToAddResponse(PaymentSchedule paymentSchedule) {
        return new AddPaymentScheduleDTOResponse(
                paymentSchedule.getAccount().getAccountNumber(),
                paymentSchedule.getPaymentDate(),
                paymentSchedule.getMonthlyPayment(),
                paymentSchedule.getSurcharge(),
                getSum(paymentSchedule),
                false
        );
    }

    private BigDecimal getSum(PaymentSchedule p) {
        return p.getMonthlyPayment().add(p.getSurcharge());
    }

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
                .orElseThrow(() -> new NearestPaymentNotFoundException(ErrorsMessage.NOT_FOUND_NEAREST_PAYMENT_MESSAGE));
    }


    public PaymentSchedule convertFromCreditAndProduct(Account account) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setAccount(account);
        paymentSchedule.setSurcharge(ZERO);
        paymentSchedule.setPaid(false);
        return paymentSchedule;
    }


    public BigDecimal[] calculatePayment(Credit credit, Product product) {
        int monthsTemp = credit.getPeriodMonth();
        BigDecimal[] payments = new BigDecimal[monthsTemp];

        if (product.getCalculationType().equals(ANNUITY)) {

            BigDecimal monthlyInterestRate = credit.getInterestRate().divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

            for (int month = 0; month < monthsTemp; month++) {
                payments[month] = AnnuityCalculator.calculate(credit.getCreditSum(),
                       monthlyInterestRate, monthsTemp);
            }

        } else {

            BigDecimal convertedInterestRate = credit.getInterestRate().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP);
            BigDecimal monthlyInterestRate = convertedInterestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

            for (int month = 0; month < monthsTemp; month++) {
                payments[month] = DifferentiatedPaymentCalculator.calculateEMI(credit.getCreditSum(),
                        monthlyInterestRate, monthsTemp, month + 1);
            }

        }
        return payments;
    }

}
