package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.operation.GetOperationsServiceImp;

import java.util.List;

public interface OperationFabric {
    OperationService<OperationResponseDTO, AddOperationRequestSpendingOrReplenishment> addOperation();

    GetOperationsServiceImp activateGetOperation();

    OperationService<OperationResponseDTO, SearchAndDeleteOperationRequest> searchOperation();

    OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation();
}
