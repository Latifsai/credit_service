package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationNotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchOperationServiceImp implements OperationService<OperationResponseDTO, UUID> {

    private final OperationRepository repository;
    private final OperationUtils util;

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
