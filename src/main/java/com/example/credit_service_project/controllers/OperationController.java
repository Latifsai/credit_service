package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.fabrics.operation.OperationFabricImp;
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
    private final OperationFabricImp fabric;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OperationResponseDTO> add() {
        return fabric.addPaymentOperation().execute();
    }

    @PostMapping("/early_payment")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO add(@RequestBody AddOperationEarlyPaymentRequest request) {
        return fabric.earlyPaymentOperationService().execute(request);
    }

    // реализовать обьединение запросов
    @PostMapping("/refill")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO add(@RequestBody AddOperationReplenishmentRequest request) {
        return fabric.replenishmentOperationService().execute(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OperationResponseDTO> get(@RequestBody GetBelongsAccountOperationsRequest request) {
        return fabric.getBelongsAccountOperations().execute(request);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO update(@RequestBody UpdateOperationsRequest request) {
        return fabric.updateOperation().execute(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OperationResponseDTO search(@PathVariable("id") @NotNull UUID id) {
        return fabric.searchOperation().execute(id);
    }



}
