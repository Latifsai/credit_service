package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.service.OperationService;

import java.util.List;

public interface OperationFabric {
    OperationService<AddOperationResponse, AddOperationRequestSpendingOrReplenishment> addOperation();

    OperationService<List<OperationResponseDTO>, GetOperationsListRequest> activateGetOperation();

    OperationService<SearchOperationResponse, SearchAndDeleteOperationRequest> searchOperation();

    OperationService<UpdateOperationsResponse, UpdateOperationsRequest> updateOperation();
}
