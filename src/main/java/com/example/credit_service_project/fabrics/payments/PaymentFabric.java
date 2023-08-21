package com.example.credit_service_project.fabrics.payments;

import com.example.credit_service_project.DTO.paymentDTO.*;
import com.example.credit_service_project.service.PaymentScheduleService;

public interface PaymentFabric {
    PaymentScheduleService<PaymentResponseDTO, PaymentsBelongsToAccountRequest> getNearestPayment();

    PaymentScheduleService<GetBelongsPaymentsResponse, PaymentsBelongsToAccountRequest> getBelongsPayments();
}
