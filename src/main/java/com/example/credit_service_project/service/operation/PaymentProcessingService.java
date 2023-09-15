package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.account.GetAllAccountsService;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.card.CardCreateService;
import com.example.credit_service_project.service.card.CardSearchService;
import com.example.credit_service_project.service.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.credit.CreditCreateService;
import com.example.credit_service_project.service.credit.CreditSearchService;
import com.example.credit_service_project.service.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessingService {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final CheckUnpaidPaymentsBelongsCreditService getUnpaidPaymentsService;
    private final AccountUpdateService updateAccountService;
    private final PaymentScheduleGeneratorService saveService;
    private final CardSearchService searchCardService;
    private final CardCreateService createCardService;
    private final GetAllAccountsService getAllAccountsService;
    private final CreditCreateService creditService;
    private final CreditOrderCreateService addCreditOrderService;
    private final AgreementCreateService createAgreementService;
    private final CreditSearchService creditSearchService;

    @Scheduled(cron = "0 0 23 * * *")
    public List<OperationResponseDTO> handlePayments() {
        LocalDate currentDate = LocalDate.now();

        List<Account> accounts = getAllAccountsService.findAllAccounts();
        List<OperationResponseDTO> donePaymentsList = new ArrayList<>();

        if (accounts.isEmpty()) {
            throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_LIST_MESSAGE);
        }

        for (Account account : accounts) {

            List<PaymentSchedule> paymentSchedules = getUnpaidPaymentsService.findUnpaidPaymentByAccount(account);

            for (PaymentSchedule paymentSchedule : paymentSchedules) {
                assert paymentSchedule != null;

                if (paymentSchedule.getPaymentDate().equals(currentDate)) {
                    Card card = searchCardService.findByAccount(account);
                    Account accountAfterOperation = util.payBill(account, paymentSchedule, card);

                    if (checkIfAccountBalanceLessThanPayment(account, paymentSchedule)) {
                        handleImmediateFine(account, paymentSchedule);
                    } else {
                        handleSuccessfulPayment(donePaymentsList, accountAfterOperation, paymentSchedule);
                    }
                    updateAccountService.saveUpdatedAccount(accountAfterOperation);
                    saveService.save(paymentSchedule);
                    createCardService.saveCard(card);
                }

                if (!getCredit(account).getFine().equals(BigDecimal.ZERO) && checkIfAccountBalanceLessThanPayment(account, paymentSchedule)) {
                    handleDelayedFine(paymentSchedule, account);
                } else if (account.getBalance().compareTo(paymentSchedule.getMonthlyPayment()) >= 0) {
                    handleSuccessfulPayment(donePaymentsList, account, paymentSchedule);
                }

            }

            if (account.getUnpaidCreditSum().compareTo(BigDecimal.ZERO) == 0) {
                closePaidCredit(account);
            }
        }

        log.info("Get all the execution of the operation today:{}", donePaymentsList);
        return donePaymentsList;
    }

    private boolean checkIfAccountBalanceLessThanPayment(Account account, PaymentSchedule payment) {
        return account.getBalance().compareTo(payment.getMonthlyPayment()) < 0;
    }

    private Credit getCredit(Account account) {
        return creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE);
    }

    private void closePaidCredit(Account account) {
        Credit credit = getCredit(account);

        credit.setCreditStatus(CreditStatus.CLOSED);
        credit.getCreditOrder().setCreditOrderStatus(CreditOrderStatus.CLOSED);
        credit.getAgreement().setActive(false);

        creditService.saveCredit(credit);
        addCreditOrderService.saveOrder(credit.getCreditOrder());
        createAgreementService.saveAgreement(credit.getAgreement());
    }

    private void handleImmediateFine(Account account, PaymentSchedule paymentSchedule) {
        int dayOfDelay = 1;
        BigDecimal fine = util.calculateFine(getCredit(account).getInterestRate(), paymentSchedule.getMonthlyPayment(), dayOfDelay);

        paymentSchedule.setSurcharge(fine);
        getCredit(account).setFine(fine);
        creditService.saveCredit(getCredit(account));
    }

    private void handleSuccessfulPayment(List<OperationResponseDTO> donePaymentsList, Account account, PaymentSchedule paymentSchedule) {
        Operation operation = util.convertDataToOperationForPayment(account, paymentSchedule);
        Operation savedOperation = saveOperation(operation);
        donePaymentsList.add(util.convertOperationToResponseDTO(savedOperation));
    }

    private void handleDelayedFine(PaymentSchedule paymentSchedule, Account account) {
        int dayOfDelay = (int) ChronoUnit.DAYS.between(paymentSchedule.getPaymentDate(), LocalDate.now());
        BigDecimal fine = util.calculateFine(getCredit(account).getInterestRate(), paymentSchedule.getMonthlyPayment(), dayOfDelay);

        paymentSchedule.setSurcharge(fine);
        getCredit(account).setFine(fine);
        creditService.saveCredit(getCredit(account));
        saveService.save(paymentSchedule);
    }

    public Operation saveOperation(Operation operation) {
        return repository.save(operation);
    }

}
