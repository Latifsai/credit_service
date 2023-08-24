package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.services.OperationService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOperationsServiceImp implements OperationService<List<OperationResponseDTO>, GetBelongsAccountOperationsRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final SearchAccountsServiceImp searchAccountsService;

    @Transactional(readOnly = true)
    @Override
    public List<OperationResponseDTO> execute(GetBelongsAccountOperationsRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        return repository.findAllByAccount(account).stream()
                .map(util::convertOperationToResponseDTO)
                .toList();
    }
}
