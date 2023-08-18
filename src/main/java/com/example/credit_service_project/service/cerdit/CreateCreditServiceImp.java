package com.example.credit_service_project.service.cerdit;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.CreditService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.service.creditOrder.SearchCreditOrderServiceImp;
import com.example.credit_service_project.service.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCreditServiceImp implements CreditService<AddCreditDTOResponse, AddCreditDTORequest> {

    private final CreditRepository repository;
    private final CreditUtil util;
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchAgreementServiceImp searchAgreementService;
    private final SearchCreditOrderServiceImp searchCreditOrderService;

    @Override
    public AddCreditDTOResponse execute(AddCreditDTORequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Agreement agreement = searchAgreementService.findById(request.getAgreementID());
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());

        Credit credit = util.createCreditFromData(request, account, agreement, creditOrder);

        return util.convertResponse(credit);
    }
}
