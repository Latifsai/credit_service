package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.services.operation.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final PaymentProcessingService createPaymentOperation;
    private final ReplenishmentAndEarlyPaymentOperationService replenishmentAndEarlyPaymentOperation;
    private final OperationUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OperationResponseDTO> add() {
        return createPaymentOperation.handlePayments();
    }

    @PostMapping("/elective")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO add(@RequestBody PaymentsOperationRequest request) {
        return replenishmentAndEarlyPaymentOperation.performOperation(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO update(@RequestBody UpdateOperationsRequest request) {
        return update.updateOperation(request);
    }

}
