package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationSearchService {

    private final OperationRepository repository;
    private final OperationUtils util;

    @Transactional(readOnly = true)
    public OperationResponseDTO searchOperation(UUID id) {
        Operation operation = findById(id);
        return util.convertOperationToResponseDTO(operation);
    }

    public Operation findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
