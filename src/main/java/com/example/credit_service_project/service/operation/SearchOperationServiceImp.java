package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.SearchAndDeleteOperationRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchOperationServiceImp implements OperationService<SearchOperationResponse, SearchAndDeleteOperationRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;
    @Override
    public SearchOperationResponse execute(SearchAndDeleteOperationRequest request) {
        Optional<Operation> operation = repository.findByIdAndDebit(request.getId(), request.isDebit());
        return operation.map(o -> util.convertOperationToSearchResponse(o))
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
