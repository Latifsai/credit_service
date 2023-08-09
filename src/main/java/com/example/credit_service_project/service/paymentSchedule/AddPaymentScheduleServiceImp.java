package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
import com.example.credit_service_project.DTO.paymentDTO.AddPaymentScheduleDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.PaymentScheduleService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.PaymentScheduleNotFoundException;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddPaymentScheduleServiceImp implements PaymentScheduleService<AddPaymentScheduleDTOResponse, AddPaymentRequestDTO> {

    private final PaymentScheduleRepository repository;
    private final PaymentScheduleUtil util;
    private final SearchAccountsServiceImp searchAccountsService;

    @Override
    public AddPaymentScheduleDTOResponse execute(AddPaymentRequestDTO request) {
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        if (account.isEmpty()) throw new PaymentScheduleNotFoundException(ErrorsMessage.UNABLE_TO_ADD_PAYMENT_MESSAGE);

        PaymentSchedule paymentSchedule = util.convertPaymentDTORequestToPayment(request, account.get());
        savePayment(paymentSchedule);
        return util.convertEntityToAddResponse(paymentSchedule);
    }

    public PaymentSchedule savePayment(PaymentSchedule paymentSchedule) {
        return repository.save(paymentSchedule);
    }
}
