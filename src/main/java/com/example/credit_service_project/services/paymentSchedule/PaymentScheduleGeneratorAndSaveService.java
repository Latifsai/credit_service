package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.PaymentScheduleRepository;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentScheduleGeneratorAndSaveService {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;

    public List<PaymentResponseDTO> generatePaymentSchedule(Credit credit, Product product, Account account) {
        List<PaymentResponseDTO> responses = new ArrayList<>();
        LocalDate firstPaymentDate = LocalDate.now().plusMonths(credit.getCreditHolidayMonthsAmount());

        int monthsTemp = credit.getPeriodMonth();
        BigDecimal[] payments = util.calculatePayment(monthsTemp, credit.getInterestRate(), credit.getCreditSum(), product);
        for (int month = 0; month < monthsTemp; month++) {
            PaymentSchedule paymentSchedule = util.convertFromCreditAndProduct(account);
            paymentSchedule.setMonthlyPayment(payments[month]);

            paymentSchedule.setPaymentDate(firstPaymentDate.plusMonths((month + 1)));

            save(paymentSchedule);
            responses.add(util.convertEntityToPaymentResponse(paymentSchedule));
        }
        log.info("Generate payment schedule for a period of: {} and for a sum of: {}", monthsTemp, credit.getCreditSum());
        return responses;
    }

    public PaymentSchedule save(PaymentSchedule paymentSchedule) {
        return repository.save(paymentSchedule);
    }
}
