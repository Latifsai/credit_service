package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.card.CardCreateService;
import com.example.credit_service_project.service.card.CardSearchService;
import com.example.credit_service_project.service.credit.CreditCreateService;
import com.example.credit_service_project.service.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.credit.CreditSearchService;
import com.example.credit_service_project.service.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.EarlyPaymentException;
import com.example.credit_service_project.validation.exceptions.OperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;
import static com.example.credit_service_project.entity.enums.OperationType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplenishmentAndEarlyPaymentOperationService {
    private final AccountSearchService accountSearchService;
    private final CardSearchService cardSearchService;
    private final OperationRepository repository;
    private final OperationUtils util;
    private final AccountUpdateService updateAccountService;
    private final CardCreateService updateCardService;
    private final CheckUnpaidPaymentsBelongsCreditService checkUnpaidPaymentsBelongsCreditService;
    private final PaymentScheduleGeneratorService saveService;
    private final CreditCreateService addCreditService;
    private final CreditOrderCreateService addCreditOrderService;
    private final AgreementCreateService addAgreementService;
    private final CreditSearchService creditSearchService;

    /**
     * The method implements operations, if the operation is a replenishment, then the deposited amount is cleared to the bank
     * Or the operation may be an early repayment of the loan. In this case, if the amount matches the parameter, then the loan is closed
     * @param request PaymentsOperationRequest
     * @return OperationResponseDTO
     */
    public OperationResponseDTO performOperation(PaymentsOperationRequest request) {
        if (request.getSum().intValueExact() < 0) {
            throw new OperationException(ErrorsMessage.NEGATIVE_SUM_EXCEPTION);
        }

        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Card card = cardSearchService.findByAccount(account);

        if (request.getType().equals(REPLENISHMENT)) {

            BigDecimal newBalance = account.getBalance().add(request.getSum());
            account.setBalance(newBalance);
            card = util.changerCardBalance(account, card);

        } else if (request.getType().equals(EARLY_REPAYMENT)) {
            Credit credit = creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE);
            if (credit.getCreditOrder().getProduct().isEarlyRepayment()) {
                account = util.payEarlyPayment(request, account, card);
                List<PaymentSchedule> unpaidPayments = checkUnpaidPaymentsBelongsCreditService.findUnpaidPaymentByAccount(account);
                for (PaymentSchedule paymentSchedule : unpaidPayments) {
                    paymentSchedule.setPaid(true);
                    paymentSchedule.setActualPaymentDate(LocalDate.now());
                    saveService.save(paymentSchedule);
                }
                credit.setCreditStatus(CreditStatus.CLOSED);
                credit.getCreditOrder().setCreditOrderStatus(CreditOrderStatus.CLOSED);
                credit.getAgreement().setActive(false);
                credit.getAgreement().setTerminationDate(LocalDate.now());

                addCreditService.saveCredit(credit);
                addCreditOrderService.saveOrder(credit.getCreditOrder());
                addAgreementService.saveAgreement(credit.getAgreement());

            } else {
                throw new EarlyPaymentException(ErrorsMessage.EARLY_PAYMENT_EXCEPTION_MESSAGE);
            }
        }
        updateAccountService.saveUpdatedAccount(account);
        updateCardService.saveCard(card);
        Operation operation = util.convertPaymentsOperationRequestToOperation(request, account);
        Operation savedOperation = repository.save(operation);

        log.info("Perform operation with ID: {}", savedOperation.getId());
        return util.convertOperationToResponseDTO(savedOperation);
    }
}
