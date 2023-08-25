package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.services.PaymentScheduleService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBelongsToTheAccountPaymentsListServiceImp implements PaymentScheduleService<GetBelongsPaymentsResponse, PaymentsBelongsToAccountRequest> {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;

    @Transactional(readOnly = true)
    @Override
    public GetBelongsPaymentsResponse execute(PaymentsBelongsToAccountRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        List<PaymentSchedule> listOfBelongsToAccountPayments = findAllByAccount(account);

        List<PaymentResponseDTO> list = listOfBelongsToAccountPayments.stream()
                .map(util::convertEntityToPaymentResponse)
                .toList();

        return new GetBelongsPaymentsResponse(account.getId(),
                account.getAccountNumber(), list);
    }

    public List<PaymentSchedule> findAllByAccount(Account account) {
        return repository.findAllByAccount(account);
    }
}
