package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.service.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationUpdateService {

    private final OperationSearchService searchOperationService;
    private final PaymentProcessingService addOperationService;
    private final OperationUtils util;

    public OperationResponseDTO updateOperation(UpdateOperationsRequest request) {
        Operation operation = searchOperationService.findById(request.getId());
        Operation updatedOperation = util.updateOperation(operation, request);
        addOperationService.saveOperation(updatedOperation);
        log.info("Update operation with ID: {}", updatedOperation.getId());
        return util.convertOperationToResponseDTO(updatedOperation);
    }
}
