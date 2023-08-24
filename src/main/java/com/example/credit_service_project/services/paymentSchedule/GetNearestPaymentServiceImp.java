package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.services.PaymentScheduleService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetNearestPaymentServiceImp implements PaymentScheduleService<PaymentResponseDTO, PaymentsBelongsToAccountRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final PaymentScheduleUtil util;

    @Transactional(readOnly = true)
    @Override
    public PaymentResponseDTO execute(PaymentsBelongsToAccountRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        PaymentSchedule nearestPayment = getNearestPayment(account);
        return util.convertEntityToPaymentResponse(nearestPayment);
    }

    public PaymentSchedule getNearestPayment(Account account) {
        return util.getNearestPaymentSchedule(account);
    }
}
