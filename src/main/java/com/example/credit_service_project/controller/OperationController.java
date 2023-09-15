package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.service.operation.OperationUpdateService;
import com.example.credit_service_project.service.operation.PaymentProcessingService;
import com.example.credit_service_project.service.operation.ReplenishmentAndEarlyPaymentOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final PaymentProcessingService createPaymentOperation;
    private final ReplenishmentAndEarlyPaymentOperationService replenishmentAndEarlyPaymentOperation;
    private final OperationUpdateService update;

    /**
     *The method collects payments from all accounts and if the plaid is under a temporary charge, then they are executed
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OperationResponseDTO> handlePayments() {
        return createPaymentOperation.handlePayments();
    }

    /**
     * The method receives a request for a full repayment of a credit,
     * determines the type of request (specified in the body of the request) and returns the result of the operation of the service
     */
    @PostMapping("/elective")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO addNewOperation(@RequestBody PaymentsOperationRequest request) {
        return replenishmentAndEarlyPaymentOperation.performOperation(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO update(@RequestBody UpdateOperationsRequest request) {
        return update.updateOperation(request);
    }

}
