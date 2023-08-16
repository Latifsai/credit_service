package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.fabrics.operation.OperationFabricImp;
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<OperationResponseDTO> add(@RequestBody AddOperationPaymentRequest request) {
        return fabric.addPaymentOperation().execute(request);
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
    public OperationResponseDTO search(@PathVariable("id") UUID id) {
        return fabric.searchOperation().execute(id);
    }
}
