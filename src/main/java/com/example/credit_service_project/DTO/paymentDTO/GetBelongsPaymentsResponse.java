package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class GetBelongsPaymentsResponse {
    UUID accountID;
    String ownerName;
    List<PaymentResponseDTO> list;
}
