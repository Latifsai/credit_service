package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.dto.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.agreement.SearchAgreementService;
import com.example.credit_service_project.service.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.service.utils.CreditUtil;
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
    private final SearchAgreementService searchAgreementService;
    private final CreditOrderSearchService searchCreditOrderService;
    private final AccountUpdateService updateAccountService;
    private final AgreementCreateService updateAgreementService;
    private final PaymentScheduleGeneratorService paymentScheduleGeneratorService;

    /**
     * Method creates a new loan based on the data from the request, and also creates a payment schedule for the entire loan term
     * @param request CreateCreditRequestDTO
     * @return CreateCreditDTOResponse
     */
    public CreateCreditDTOResponse createCredit(CreateCreditRequestDTO request) {
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

    /**
     * Save Credit in DB
     * @param credit Credit
     * @return Credit
     */
    public Credit saveCredit(Credit credit) {
        return repository.save(credit);
    }

    /**
     * The method checks whether the account has active loans
     * @param account Account
     */
    private void checkActiveCredit(Account account) {
        if (!account.getCredits().isEmpty()) {
            List<Credit> getActiveCreditBelongsAccounts = repository.findByAccountAndCreditStatus(account, ACTIVE);
            if (getActiveCreditBelongsAccounts.size() == 1) {
                throw new IsAlreadyExistException(ErrorsMessage.CREDIT_IS_ALREADY_EXIST_EXCEPTION_MESSAGE);
            }
        }
    }

}
