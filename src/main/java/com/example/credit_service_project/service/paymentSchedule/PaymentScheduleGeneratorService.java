package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleGeneratorService {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;

    public List<PaymentResponseDTO> execute(Credit credit, Product product, Account account) {
        List<PaymentResponseDTO> responses = new ArrayList<>();

        int monthsTemp = credit.getPeriodMonth();
        BigDecimal[] payments = util.calculatePayment(credit, product);
        for (int month = 0; month < monthsTemp; month++) {
            PaymentSchedule paymentSchedule = util.convertFromCreditAndProduct(account);
            paymentSchedule.setMonthlyPayment(payments[month]);
            paymentSchedule.setPaymentDate(LocalDate.now().plusMonths((month + 1)));
            repository.save(paymentSchedule);
            responses.add(util.convertEntityToPaymentResponse(paymentSchedule));
        }
        return responses;
    }
}
