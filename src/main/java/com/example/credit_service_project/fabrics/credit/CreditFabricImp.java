package com.example.credit_service_project.fabrics.credit;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.CreditService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.agreement.CreateAgreementServiceImp;
import com.example.credit_service_project.service.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.service.cerdit.CreateCreditServiceImp;
import com.example.credit_service_project.service.cerdit.GetAllCreditsService;
import com.example.credit_service_project.service.creditOrder.SearchCreditOrderServiceImp;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
import com.example.credit_service_project.service.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditFabricImp implements CreditFabric {

    private final CreditRepository repository;
    private final CreditUtil util;
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchAgreementServiceImp searchAgreementService;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final UpdateAccountServiceImp updateAccountService;
    private final CreateAgreementServiceImp updateAgreementService;
    private final PaymentScheduleGeneratorAndSaveService paymentScheduleGeneratorService;

    @Override
    public GetAllCreditsService get() {
        return new GetAllCreditsService(repository, util);
    }

    @Override
    public CreditService<AddCreditDTOResponse, AddCreditDTORequest> add() {
        return new CreateCreditServiceImp(repository, util, searchAccountsService, searchAgreementService,
                searchCreditOrderService, updateAccountService, updateAgreementService,
                paymentScheduleGeneratorService);
    }
}
