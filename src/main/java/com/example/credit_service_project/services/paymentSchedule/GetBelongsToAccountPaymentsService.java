package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBelongsToAccountPaymentsService {

    private final PaymentScheduleUtil util;
    private final PaymentScheduleRepository repository;
    private final AccountSearchService accountSearchService;

    @Transactional(readOnly = true)
    public GetBelongsPaymentsResponse getBelongsToAccountPayments(PaymentsBelongsToAccountRequest request) {
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
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
