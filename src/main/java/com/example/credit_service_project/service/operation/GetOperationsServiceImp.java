package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetOperationsServiceImp {

    private final OperationRepository repository;
    private final OperationUtils util;

    public List<OperationResponseDTO> execute() {
        var list = repository.findAll();
        return list.stream()
                .map(o -> util.convertOperationToResponseDTO(o))
                .toList();
    }
}
