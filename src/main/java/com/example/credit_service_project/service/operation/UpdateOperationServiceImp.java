package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.OperationNotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateOperationServiceImp implements OperationService<OperationResponseDTO, UpdateOperationsRequest> {

    private final SearchOperationServiceImp searchOperationService;
    private final AddOperationServiceImp addOperationService;
    private final OperationUtils util;

    @Override
    public OperationResponseDTO execute(UpdateOperationsRequest request) {
        Optional<Operation> operation = searchOperationService.findOperationByIdAndDebit(request.getId(), request.isDebit());
        return operation.map(o -> {
            var updatedOperation = util.updateOperation(o, request);
            addOperationService.save(updatedOperation);
            return util.convertOperationToResponseDTO(updatedOperation);
        }).orElseThrow(() -> new OperationNotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
