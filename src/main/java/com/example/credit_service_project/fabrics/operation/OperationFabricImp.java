package com.example.credit_service_project.fabrics.operation;

import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.GetAccountsListServiceImp;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.agreement.CreateAgreementServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.cerdit.CreateCreditServiceImp;
import com.example.credit_service_project.service.cerdit.GetAllUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.creditOrder.AddCreditOrderServiceImp;
import com.example.credit_service_project.service.operation.*;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
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
    private final PaymentScheduleGeneratorAndSaveService generatorAndSaveService;
    private final SearchOperationServiceImp searchOperationService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;
    private final GetAccountsListServiceImp getAccountsListService;
    private final GetAllUnpaidPaymentsBelongsCreditService getAllUnpaidPaymentsBelongsCreditService;
    private final CreateCreditServiceImp addCreditService;
    private final AddCreditOrderServiceImp addCreditOrderService;
    private final CreateAgreementServiceImp addAgreementService;


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
    public AddPaymentOperationServiceImp addPaymentOperation() {
        return new AddPaymentOperationServiceImp(repository, util, getAllUnpaidPaymentsBelongsCreditService,
                updateAccountService, generatorAndSaveService, searchCardService, createCardService,
                getAccountsListService, addCreditService, addCreditOrderService, addAgreementService);
    }

    @Override
    public OperationService<OperationResponseDTO, PaymentsOperationRequest> electorOperation() {
        return new ReplenishmentAndEarlyPaymentOperationService(searchAccountsService, searchCardService, repository, util,
                updateAccountService, createCardService, getAllUnpaidPaymentsBelongsCreditService, generatorAndSaveService,
                addCreditService, addCreditOrderService, addAgreementService);
    }

}
