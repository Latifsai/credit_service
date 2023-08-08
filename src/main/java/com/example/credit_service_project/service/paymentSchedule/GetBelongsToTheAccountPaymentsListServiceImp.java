package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.PaymentScheduleService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GetBelongsToTheAccountPaymentsListServiceImp
        implements PaymentScheduleService<GetBelongsPaymentsResponse, PaymentsBelongsToAccountRequest> {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;

    @Override
    public GetBelongsPaymentsResponse execute(PaymentsBelongsToAccountRequest request) {
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (account.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);

        var listOfBelongsToAccountPayments = repository.findAllByAccount(account.get());

        List<PaymentResponseDTO> list = listOfBelongsToAccountPayments.stream()
                .map(paymentSchedule -> util.convertEntityToPaymentResponse(paymentSchedule))
                .toList();

        return new GetBelongsPaymentsResponse(account.get().getId(), account.get().getCard().getHolderName(), list);
    }
}
