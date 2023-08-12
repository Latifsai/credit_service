package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.SearchOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.fabrics.operation.OperationFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationFabricImp fabric;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO add(@RequestBody AddOperationPaymentRequest request) {
        return fabric.addPaymentOperation().execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OperationResponseDTO> get() {
        return fabric.activateGetOperation().execute();

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationResponseDTO update(@RequestBody UpdateOperationsRequest request) {
        return fabric.updateOperation().execute(request);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public OperationResponseDTO search(@RequestBody SearchOperationRequest request) {
        return fabric.searchOperation().execute(request);
    }
}
