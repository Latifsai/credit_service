package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateOperationServiceImp implements OperationService<OperationResponseDTO, UpdateOperationsRequest> {

    private final SearchOperationServiceImp searchOperationService;
    private final AddPaymentOperationServiceImp addOperationService;
    private final OperationUtils util;

    @Override
    public OperationResponseDTO execute(UpdateOperationsRequest request) {
        Operation operation = searchOperationService.findById(request.getId());
        Operation updatedOperation = util.updateOperation(operation, request);
        addOperationService.saveOperation(updatedOperation);
        return util.convertOperationToResponseDTO(updatedOperation);
    }
}
