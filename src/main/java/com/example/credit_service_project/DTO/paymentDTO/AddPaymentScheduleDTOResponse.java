package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.util.List;

@Value
public class AddPaymentScheduleDTOResponse {
    String accountNumber;
    String agreementNumber;
    String ProductName;
    List<PaymentDTO> paymentsList;

}
