package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.GetOperationsListRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetOperationsServiceImp implements OperationService<List<OperationResponseDTO>, GetOperationsListRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;

    @Override
    public List<OperationResponseDTO> execute(GetOperationsListRequest request) {
        var list = repository.findAllByDebit(request.isDebit());
        return list.stream()
                .map(o -> util.convertOperationToResponseDTO(o))
                .toList();
    }
}
