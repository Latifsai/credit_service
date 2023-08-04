package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.operation.*;
import com.example.credit_service_project.service.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationFabricImp implements OperationFabric {
    private final OperationRepository repository;
    private final AccountRepository accountRepository;
    private final OperationUtils util;


    @Override
    public OperationService<AddOperationResponse, AddOperationRequestSpendingOrReplenishment> addOperation() {
        return new AddOperationServiceImp(repository, accountRepository, util);
    }

    @Override
    public OperationService<List<OperationResponseDTO>, GetOperationsListRequest> activateGetOperation() {
        return new GetOperationsServiceImp(repository, util);
    }

    @Override
    public OperationService<SearchOperationResponse, SearchAndDeleteOperationRequest> searchOperation() {
        return new SearchOperationServiceImp(repository, util);
    }

    @Override
    public OperationService<UpdateOperationsResponse, UpdateOperationsRequest> updateOperation() {
        return new UpdateOperationServiceImp(repository, util);
    }
}
