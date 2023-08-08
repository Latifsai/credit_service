package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.service.PaymentScheduleService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetNearestPaymentServiceImp implements PaymentScheduleService<PaymentResponseDTO, PaymentsBelongsToAccountRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final PaymentScheduleUtil util;

    @Override
    public PaymentResponseDTO execute(PaymentsBelongsToAccountRequest request) {
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (account.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);

        PaymentSchedule nearestPayment = getNearestPayment(account.get());
        return util.convertEntityToPaymentResponse(nearestPayment);
    }

    public PaymentSchedule getNearestPayment(Account account) {
        return util.getNearestPaymentSchedule(account);
    }
}
