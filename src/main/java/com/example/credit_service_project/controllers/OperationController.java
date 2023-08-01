package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.operationDTO.AddOperationRequestSpendingOrReplenishment;
import com.example.credit_service_project.DTO.operationDTO.GetOperationsListRequest;
import com.example.credit_service_project.DTO.operationDTO.SearchAndDeleteOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.fabrics.operation.OperationFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationFabricImp fabric;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddOperationRequestSpendingOrReplenishment request) {
        var response = fabric.addOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody SearchAndDeleteOperationRequest request) {
        var response = fabric.deleteOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestBody GetOperationsListRequest request) {
        var response = fabric.activateGetOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateOperationsRequest request) {
        var response = fabric.updateOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchAndDeleteOperationRequest request) {
        var response = fabric.searchOperation().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
