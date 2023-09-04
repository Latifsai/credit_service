package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreateCreditDTORequest;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repositories.CreditRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.services.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
import com.example.credit_service_project.services.utils.CreditUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.IsAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCreateService {

    private final CreditRepository repository;
    private final CreditUtil util;
    private final AccountSearchService accountSearchService;
    private final SearchAgreementServiceImp searchAgreementService;
    private final CreditOrderSearchService searchCreditOrderService;
    private final AccountUpdateService updateAccountService;
    private final AgreementCreateService updateAgreementService;
    private final PaymentScheduleGeneratorAndSaveService paymentScheduleGeneratorService;

    public AddCreditDTOResponse createCredit(CreateCreditDTORequest request) {
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        checkActiveCredit(account);

        Agreement agreement = searchAgreementService.findById(request.getAgreementID());
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());

        Credit credit = util.createCreditFromData(request, account, agreement, creditOrder);

        updateAccountService.saveUpdatedAccount(account);
        updateAgreementService.saveAgreement(agreement);
        Credit savedCredit = saveCredit(credit);

        List<PaymentResponseDTO> list = paymentScheduleGeneratorService.generatePaymentSchedule(credit,
                credit.getCreditOrder().getProduct(), account, agreement);

        log.info("Create and save credit with ID: {}", credit.getId());
        return util.convertResponse(savedCredit, list);
    }

    public Credit saveCredit(Credit credit) {
        return repository.save(credit);
    }


    private void checkActiveCredit(Account account) {
        if (!account.getCredits().isEmpty()) {
            List<Credit> getActiveCreditBelongsAccounts = repository.findByAccountAndCreditStatus(account, ACTIVE);
            if (getActiveCreditBelongsAccounts.size() == 1) {
                throw new IsAlreadyExistException(ErrorsMessage.CREDIT_IS_ALREADY_EXIST_EXCEPTION_MESSAGE);
            }
        }
    }
}
