package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.operation.AddPaymentOperationServiceImp;
import com.example.credit_service_project.service.operation.GetOperationsServiceImp;
import com.example.credit_service_project.service.operation.SearchOperationServiceImp;
import com.example.credit_service_project.service.operation.UpdateOperationServiceImp;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OperationFabricImp implements OperationFabric {

    private final OperationRepository repository;
    private final AddPaymentOperationServiceImp saveService;
    private final OperationUtils util;
    private final UpdateAccountServiceImp updateAccountService;
    private final SearchAccountsServiceImp searchAccountsService;
    private final GetNearestPaymentServiceImp getNearestPaymentService;
    private final AddPaymentScheduleServiceImp addPaymentScheduleService;
    private final SearchOperationServiceImp searchOperationService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;


    @Override
    public OperationService<List<OperationResponseDTO>, GetBelongsAccountOperationsRequest> getBelongsAccountOperations() {
        return new GetOperationsServiceImp(repository, util, searchAccountsService);
    }

    @Override
    public OperationService<OperationResponseDTO, UUID> searchOperation() {
        return new SearchOperationServiceImp(repository, util);
    }

    @Override
    public OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation() {
        return new UpdateOperationServiceImp(searchOperationService, saveService, util);
    }

    @Override
    public OperationService<OperationResponseDTO, AddOperationPaymentRequest> addPaymentOperation() {
        return new AddPaymentOperationServiceImp(repository, util, getNearestPaymentService, searchAccountsService,
                updateAccountService, addPaymentScheduleService, searchCardService, createCardService);
    }
}
