package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.SearchOperationRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.OperationNotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchOperationServiceImp implements OperationService<OperationResponseDTO, SearchOperationRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;

    @Override
    public OperationResponseDTO execute(SearchOperationRequest request) {
        Optional<Operation> operation = findOperationByIdAndDebit(request.getId(), request.isDebit());
        return operation.map(o -> util.convertOperationToResponseDTO(o))
                .orElseThrow(() -> new OperationNotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }

    public Optional<Operation> findOperationByIdAndDebit(UUID id, boolean isDebit) {
        return repository.findByIdAndDebit(id, isDebit);
    }
}
