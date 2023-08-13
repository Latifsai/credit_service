package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
import com.example.credit_service_project.DTO.paymentDTO.AddPaymentScheduleDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NearestPaymentNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

@Component
public class PaymentScheduleUtil {

    public PaymentSchedule convertPaymentDTORequestToPayment(AddPaymentRequestDTO request, Account account) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();

        paymentSchedule.setPaymentDate(request.getPaymentDate());
        paymentSchedule.setSurcharge(BigDecimal.ZERO);
        paymentSchedule.setMainPayment(request.getMainPayment());
        paymentSchedule.setRatePayment(request.getRatePayment());
        paymentSchedule.setPaid(false);
        paymentSchedule.setAccount(account);
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


}
