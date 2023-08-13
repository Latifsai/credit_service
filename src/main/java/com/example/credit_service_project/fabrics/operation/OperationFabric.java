package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.operation.GetOperationsServiceImp;

import java.util.List;
import java.util.UUID;

public interface OperationFabric {

    OperationService<List<OperationResponseDTO>, GetBelongsAccountOperationsRequest> getBelongsAccountOperations();

    OperationService<OperationResponseDTO, UUID> searchOperation();

    OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation();

    OperationService<OperationResponseDTO, AddOperationPaymentRequest> addPaymentOperation();
}
