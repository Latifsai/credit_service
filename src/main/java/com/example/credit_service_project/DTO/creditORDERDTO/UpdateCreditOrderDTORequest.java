package com.example.credit_service_project.DTO.creditORDERDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

@Value
public class UpdateCreditOrderDTORequest {
    String number;

    Integer periodMonths;
    CreditOrderStatus creditOrderStatus;
}
