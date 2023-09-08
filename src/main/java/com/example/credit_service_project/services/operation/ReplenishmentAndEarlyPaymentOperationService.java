package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.card.CardCreateService;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.credit.CreditCreateService;
import com.example.credit_service_project.services.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.services.credit.CreditSearchService;
import com.example.credit_service_project.services.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.services.utils.OperationUtils;
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

    public OperationResponseDTO performOperation(PaymentsOperationRequest request) {

        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getNumber());
        Card card = cardSearchService.findByAccount(account);

        if (request.getType().equals(REPLENISHMENT)) {

            if (request.getSum().intValueExact() < 0) {
                throw new OperationException(ErrorsMessage.NEGATIVE_SUM_EXCEPTION);
            }

            BigDecimal newBalance = account.getBalance().add(request.getSum());
            account.setBalance(newBalance);
            card = util.changerCardBalance(account, card);

        } else if (request.getType().equals(EARLY_REPAYMENT)) {
            Credit credit = creditSearchService.searchCreditByAccountAndStatus(account,ACTIVE);
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
