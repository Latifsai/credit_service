package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.card.UpdateCardServiceImp;
import com.example.credit_service_project.service.operation.*;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationFabricImp implements OperationFabric {
    private final OperationRepository repository;
    private final OperationUtils util;
    private final UpdateAccountServiceImp updateAccountService;
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchCardServiceImp searchCardService;
    private final UpdateCardServiceImp updateCardService;
    private final GetNearestPaymentServiceImp getNearestPaymentService;
    private final AddPaymentScheduleServiceImp addPaymentScheduleService;

    @Override
    public OperationService<OperationResponseDTO, AddOperationRequestSpendingOrReplenishment> addOperation() {
        return new AddOperationServiceImp(repository, util, updateAccountService,
                searchAccountsService,searchCardService, updateCardService);
    }

    @Override
    public GetOperationsServiceImp activateGetOperation() {
        return new GetOperationsServiceImp(repository, util);
    }

    @Override
    public OperationService<OperationResponseDTO, SearchOperationRequest> searchOperation() {
        return new SearchOperationServiceImp(repository, util);
    }

    @Override
    public OperationService<OperationResponseDTO, UpdateOperationsRequest> updateOperation() {
        return new UpdateOperationServiceImp(repository, util);
    }

    @Override
    public OperationService<OperationResponseDTO, AddOperationPaymentRequest> addPaymentOperation() {
        return new AddPaymentOperationServiceImp(repository,util,getNearestPaymentService, searchAccountsService, updateAccountService, addPaymentScheduleService);
    }
}
