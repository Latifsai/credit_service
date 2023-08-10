package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.SearchOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.fabrics.operation.OperationFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationFabricImp fabric;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AddOperationPaymentRequest request) {
        var response = fabric.addPaymentOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        var response = fabric.activateGetOperation().execute();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateOperationsRequest request) {
        var response = fabric.updateOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchOperationRequest request) {
        var response = fabric.searchOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
