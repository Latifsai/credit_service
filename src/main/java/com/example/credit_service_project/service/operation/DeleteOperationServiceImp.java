package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.SearchAndDeleteOperationRequest;
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
public class DeleteOperationServiceImp implements OperationService<OperationResponseDTO, SearchAndDeleteOperationRequest> {

    private final OperationRepository repository;
    private final OperationUtils utils;

    @Override
    public OperationResponseDTO execute(SearchAndDeleteOperationRequest request) {
        var operation = repository.findByIdAndDebit(request.getId(), request.isDebit());
        return operation.map(o -> {
            var response = utils.convertOperationToResponseDTO(o);
            repository.delete(o);
            return response;
        }).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_OPERATION_MESSAGE));
    }
}
