package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetNearestPaymentService {

    private final AccountSearchService accountSearchService;
    private final PaymentScheduleUtil util;

    @Transactional(readOnly = true)
    public PaymentResponseDTO getNearestPayment(PaymentsBelongsToAccountRequest request) {
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        PaymentSchedule nearestPayment = getNearestPaymentForAccount(account);
        log.info("Get nearest payment:{}", nearestPayment);
        return util.convertEntityToPaymentResponse(nearestPayment);
    }

    public PaymentSchedule getNearestPaymentForAccount(Account account) {
        return util.getNearestPaymentSchedule(account);
    }
}
