package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
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
public class PaymentScheduleGeneratorService {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;

    /**
     *In the method, based on data on the loan term, amount, interest rate and amount,
     *  the full range of payments will be calculated, taking into account currency conversion and interest rates
     * @param credit Credit
     * @param product Product
     * @param account Account
     * @param agreement Agreement
     * @return List<PaymentResponseDTO>
     */
    public List<PaymentResponseDTO> generatePaymentSchedule(Credit credit, Product product, Account account, Agreement agreement) {

        List<PaymentResponseDTO> responses = new ArrayList<>();
        LocalDate firstPaymentDate = agreement.getAgreementDate().plusMonths(credit.getCreditHolidayMonthsAmount());
        int monthsTemp = credit.getPeriodMonth();

        BigDecimal[] payments = util.calculatePayment(monthsTemp, credit.getInterestRate(), credit.getCreditSum(), product);
        for (int month = 0; month < monthsTemp; month++) {
            PaymentSchedule paymentSchedule = util.convertToPayment(account);
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
