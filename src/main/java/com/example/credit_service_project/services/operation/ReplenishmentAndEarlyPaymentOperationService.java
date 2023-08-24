package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.services.OperationService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.account.UpdateAccountServiceImp;
import com.example.credit_service_project.services.agreement.CreateAgreementServiceImp;
import com.example.credit_service_project.services.card.CreateCardServiceImp;
import com.example.credit_service_project.services.card.SearchCardServiceImp;
import com.example.credit_service_project.services.credit.CreateCreditServiceImp;
import com.example.credit_service_project.services.credit.GetAllUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.services.creditOrder.AddCreditOrderServiceImp;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.EarlyPaymentException;
import com.example.credit_service_project.validation.exceptions.OperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.example.credit_service_project.entity.enums.OperationType.*;

@Service
@RequiredArgsConstructor
public class ReplenishmentAndEarlyPaymentOperationService implements OperationService<OperationResponseDTO, PaymentsOperationRequest> {
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchCardServiceImp cardSearchService;
    private final OperationRepository repository;
    private final OperationUtils util;
    private final UpdateAccountServiceImp updateAccountService;
    private final CreateCardServiceImp updateCardService;
    private final GetAllUnpaidPaymentsBelongsCreditService getAllUnpaidPaymentsBelongsCreditService;
    private final PaymentScheduleGeneratorAndSaveService saveService;
    private final CreateCreditServiceImp addCreditService;
    private final AddCreditOrderServiceImp addCreditOrderService;
    private final CreateAgreementServiceImp addAgreementService;


    @Override
    public OperationResponseDTO execute(PaymentsOperationRequest request) {

        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getNumber());
        Card card = cardSearchService.findByAccount(account);

        if (request.getType().equals(REPLENISHMENT)) {

            if (request.getSum().intValueExact() < 0) {
                throw new OperationException(ErrorsMessage.NEGATIVE_SUM_EXCEPTION);
            }

            BigDecimal newBalance = account.getBalance().add(request.getSum());
            account.setBalance(newBalance);
            card = util.changerCardBalance(account, card);

        } else if (request.getType().equals(EARLY_REPAYMENT)) {
            Credit credit = account.getCredit();
            if (credit.getCreditOrder().getProduct().isEarlyRepayment()) {
                account = util.payEarlyPayment(request, account, card);
                List<PaymentSchedule> unpaidPayments = getAllUnpaidPaymentsBelongsCreditService.findUnpaidPaymentByAccount(account);
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
        return util.convertOperationToResponseDTO(savedOperation);
    }
}
