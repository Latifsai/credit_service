package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.operation.AddPaymentOperationServiceImp;

import java.util.List;
import java.util.UUID;

public interface OperationFabric {

    OperationService<List<OperationResponseDTO>, GetBelongsAccountOperationsRequest> getBelongsAccountOperations();

    OperationService<OperationResponseDTO, UUID> searchOperation();

    OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation();

    AddPaymentOperationServiceImp addPaymentOperation();

    OperationService<OperationResponseDTO, PaymentsOperationRequest> electorOperation();
}
