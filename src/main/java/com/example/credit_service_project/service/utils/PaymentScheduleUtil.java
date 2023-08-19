package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
import com.example.credit_service_project.DTO.paymentDTO.AddPaymentScheduleDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.service.utils.calculators.AnnuityCalculator;
import com.example.credit_service_project.service.utils.calculators.DifferentiatedPaymentCalculator;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NearestPaymentNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        paymentSchedule.setMainPayment(request.getMainPayment());
        paymentSchedule.setRatePayment(request.getRatePayment());
        paymentSchedule.setPaid(false);
        return paymentSchedule;
    }

    public AddPaymentScheduleDTOResponse convertEntityToAddResponse(PaymentSchedule paymentSchedule) {
        return new AddPaymentScheduleDTOResponse(
                paymentSchedule.getAccount().getAccountNumber(),
                paymentSchedule.getPaymentDate(),
                paymentSchedule.getMainPayment(),
                paymentSchedule.getRatePayment(),
                paymentSchedule.getSurcharge(),
                getSum(paymentSchedule),
                false
        );
    }

    private BigDecimal getSum(PaymentSchedule p) {
        return p.getMainPayment().add(p.getRatePayment()).add(p.getSurcharge());
    }

    public PaymentResponseDTO convertEntityToPaymentResponse(PaymentSchedule paymentSchedule) {
        return new PaymentResponseDTO(
                paymentSchedule.getId(),
                paymentSchedule.getPaymentDate(),
                paymentSchedule.getActualPaymentDate(),
                paymentSchedule.getSurcharge(),
                paymentSchedule.getMainPayment(),
                paymentSchedule.getRatePayment(),
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


    public PaymentSchedule convertFromCreditAndProduct(Credit credit, Product product, Account account) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setAccount(account);

        paymentSchedule.setSurcharge(ZERO);
        paymentSchedule.setMonthlyPayment(getMonthly(credit, product));
        paymentSchedule.setPaid(false);
        return paymentSchedule;
    }


    private BigDecimal getMonthly(Credit credit, Product product) {
        int monthsTemp = credit.getPeriodMonth();
        if (product.getCalculationType().equals(ANNUITY)) {
            return AnnuityCalculator.calculate(credit.getCreditSum(), credit.getInterestRate(), monthsTemp);
        } else {
            return DifferentiatedPaymentCalculator.calculateEMI(credit.getCreditSum(), credit.getInterestRate(), monthsTemp, 1);
        }
    }

}
