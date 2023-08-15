package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.PaymentScheduleService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetBelongsToTheAccountPaymentsListServiceImp implements PaymentScheduleService<GetBelongsPaymentsResponse, PaymentsBelongsToAccountRequest> {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;

    @Override
    public GetBelongsPaymentsResponse execute(PaymentsBelongsToAccountRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        var listOfBelongsToAccountPayments = repository.findAllByAccount(account);

        List<PaymentResponseDTO> list = listOfBelongsToAccountPayments.stream()
                .map(paymentSchedule -> util.convertEntityToPaymentResponse(paymentSchedule))
                .toList();

        return new GetBelongsPaymentsResponse(account.getId(),
                account.getAccountNumber(), list);
    }
}
