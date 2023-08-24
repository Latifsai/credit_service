package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.services.CreditService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.account.UpdateAccountServiceImp;
import com.example.credit_service_project.services.agreement.CreateAgreementServiceImp;
import com.example.credit_service_project.services.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.services.creditOrder.SearchCreditOrderServiceImp;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
import com.example.credit_service_project.services.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateCreditServiceImp implements CreditService<AddCreditDTOResponse, AddCreditDTORequest> {

    private final CreditRepository repository;
    private final CreditUtil util;
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchAgreementServiceImp searchAgreementService;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final UpdateAccountServiceImp updateAccountService;
    private final CreateAgreementServiceImp updateAgreementService;
    private final PaymentScheduleGeneratorAndSaveService paymentScheduleGeneratorService;

    @Transactional
    @Override
    public AddCreditDTOResponse execute(AddCreditDTORequest request) {

        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Agreement agreement = searchAgreementService.findById(request.getAgreementID());
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());

        Credit credit = util.createCreditFromData(request, account, agreement, creditOrder);

        updateAccountService.saveUpdatedAccount(account);
        updateAgreementService.saveAgreement(agreement);
        Credit savedCredit = saveCredit(credit);

        List<PaymentResponseDTO> list = paymentScheduleGeneratorService.execute(credit, credit.getCreditOrder().getProduct(), account);
        return util.convertResponse(savedCredit, list);
    }

    public Credit saveCredit(Credit credit) {
        return repository.save(credit);
    }
}
