package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.operation.GetOperationsServiceImp;

public interface OperationFabric {
    OperationService<OperationResponseDTO, AddOperationRequestSpendingOrReplenishment> addOperation();

    GetOperationsServiceImp activateGetOperation();

    OperationService<OperationResponseDTO, SearchOperationRequest> searchOperation();

    OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation();

    OperationService<OperationResponseDTO, AddOperationPaymentRequest> addPaymentOperation();
}
