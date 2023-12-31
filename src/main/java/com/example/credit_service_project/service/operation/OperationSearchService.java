package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationSearchService {

    private final OperationRepository repository;
    private final OperationUtils util;

    /**
     * Search Operation by ID and convert to reponse
     * @param id UUID
     * @return OperationResponseDTO
     */
    @Transactional(readOnly = true)
    public OperationResponseDTO searchOperation(UUID id) {
        Operation operation = findById(id);
        log.info("Search operation with ID: {}", id);
        return util.convertOperationToResponseDTO(operation);
    }

    /**
     * Find by ID
     * @param id UUID
     * @return Operation
     */
    public Operation findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
