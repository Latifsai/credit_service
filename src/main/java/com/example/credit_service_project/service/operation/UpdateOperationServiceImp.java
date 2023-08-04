package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateOperationServiceImp implements OperationService<OperationResponseDTO, UpdateOperationsRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;

    @Override
    public OperationResponseDTO execute(UpdateOperationsRequest request) {
        var operation = repository.findByIdAndDebit(request.getId(), request.isDebit());
        return operation.map(o -> {
            var updatedOperation = util.updateOperation(o, request);
            repository.save(updatedOperation);
            return util.convertOperationToResponseDTO(updatedOperation);
        }).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
