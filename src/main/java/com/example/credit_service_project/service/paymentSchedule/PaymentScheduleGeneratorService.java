package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleGeneratorService {

    private final PaymentScheduleUtil util;
    public List<PaymentResponseDTO> execute(Credit credit, Product product, Account account) {

        PaymentSchedule paymentSchedule =

    }
}
