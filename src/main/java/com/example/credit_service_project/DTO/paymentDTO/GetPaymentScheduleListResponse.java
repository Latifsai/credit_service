package com.example.credit_service_project.DTO.paymentDTO;

import com.example.credit_service_project.entity.PaymentSchedule;
import lombok.Value;

import java.util.List;

@Value
public class GetPaymentScheduleListResponse {
    List<PaymentSchedule> list;
}
