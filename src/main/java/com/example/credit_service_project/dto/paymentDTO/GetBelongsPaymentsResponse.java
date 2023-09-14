package com.example.credit_service_project.dto.paymentDTO;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class GetBelongsPaymentsResponse {
    UUID accountID;
    String accountNumber;
    List<PaymentResponseDTO> list;
}
