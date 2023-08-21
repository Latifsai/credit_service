package com.example.credit_service_project.service.cerdit;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.paymentSchedule.GetBelongsToTheAccountPaymentsListServiceImp;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.CreditNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
// can be used by client and admin
public class GetAllUnpaidPaymentsBelongsCreditService {
    private final CreditRepository repository;
    private final GetBelongsToTheAccountPaymentsListServiceImp belongsToTheAccountPaymentsListService;
    private final PaymentScheduleUtil util;

    public List<PaymentResponseDTO> execute(UUID creditID) {
        Credit credit = repository.findById(creditID)
                .orElseThrow(() -> new CreditNotFoundException(ErrorsMessage.NOT_FOUND_CREDIT_MESSAGE));

        List<PaymentSchedule> unpaid = findUnpaidPaymentByAccount(credit.getAccount());
        return unpaid.stream()
                .map(util::convertEntityToPaymentResponse)
                .toList();
    }

    public List<PaymentSchedule> findUnpaidPaymentByAccount(Account account) {
        List<PaymentSchedule> payments = belongsToTheAccountPaymentsListService.findAllByAccount(account);

        return payments.stream()
                .filter(paymentSchedule -> !paymentSchedule.isPaid())
                .toList();
    }


}
