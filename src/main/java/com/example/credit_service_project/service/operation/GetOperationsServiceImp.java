package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetOperationsServiceImp implements OperationService<List<OperationResponseDTO>, GetBelongsAccountOperationsRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final SearchAccountsServiceImp searchAccountsService;

//    public List<OperationResponseDTO> getAll() {
//
//        return repository.findAll().stream()
//                .map(o -> util.convertOperationToResponseDTO(o))
//                .toList();
//    }

    @Override
    public List<OperationResponseDTO> execute(GetBelongsAccountOperationsRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        return repository.findAllByAccount(account).stream()
                .map(util::convertOperationToResponseDTO)
                .toList();
    }
}
