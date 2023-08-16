package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.operationDTO.AddOperationEarlyPaymentRequest;
import com.example.credit_service_project.service.operation.AddPaymentOperationServiceImp;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCheckJob implements Job {

    private final AddPaymentOperationServiceImp paymentService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        AddOperationEarlyPaymentRequest request = (AddOperationEarlyPaymentRequest) jobExecutionContext.getJobDetail().getJobDataMap().get("request");
        paymentService.execute();
    }
}
