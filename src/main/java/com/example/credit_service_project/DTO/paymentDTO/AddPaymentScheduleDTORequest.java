package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.util.List;

@Value
public class AddPaymentScheduleDTORequest {
    List<PaymentDTO> paymentsList;
    // при заполнении в цикле сохранить все платежи
}
