package com.example.credit_service_project.fabrics.payments;

import com.example.credit_service_project.DTO.paymentDTO.*;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.PaymentScheduleService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetBelongsToTheAccountPaymentsListServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFabricImp implements PaymentFabric {

    private final PaymentScheduleRepository repository;
    private final PaymentScheduleUtil util;
    private final SearchAccountsServiceImp searchAccountsService;

    @Override
    public PaymentScheduleService<PaymentResponseDTO, PaymentsBelongsToAccountRequest> getNearestPayment() {
        return new GetNearestPaymentServiceImp(searchAccountsService, util);
    }

    @Override
    public PaymentScheduleService<GetBelongsPaymentsResponse, PaymentsBelongsToAccountRequest> getBelongsPayments() {
        return new GetBelongsToTheAccountPaymentsListServiceImp(util, repository, searchAccountsService);
    }

    public PaymentScheduleService<AddPaymentScheduleDTOResponse, AddPaymentRequestDTO> addPaymentSchedule() {
        return new AddPaymentScheduleServiceImp(repository, util, searchAccountsService);
    }
}
