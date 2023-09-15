package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetOperationsService {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final AccountSearchService accountSearchService;

    @Transactional(readOnly = true)
    public List<OperationResponseDTO> getAllOperations(GetBelongsAccountOperationsRequest request) {
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        log.info("Get a list of operations");
        return repository.findAllByAccount(account).stream()
                .map(util::convertOperationToResponseDTO)
                .toList();
    }
}
