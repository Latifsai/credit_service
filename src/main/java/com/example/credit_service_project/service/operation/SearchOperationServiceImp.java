package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchOperationServiceImp implements OperationService<OperationResponseDTO, UUID> {

    private final OperationRepository repository;
    private final OperationUtils util;

    @Transactional(readOnly = true)
    @Override
    public OperationResponseDTO execute(UUID id) {
        Operation operation = findById(id)
                .orElseThrow(() -> new OperationNotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
        return util.convertOperationToResponseDTO(operation);

    }

    public Optional<Operation> findById(UUID id) {
        return repository.findById(id);
    }
}
