package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.GetAccountsListServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.cerdit.CreateCreditServiceImp;
import com.example.credit_service_project.service.cerdit.GetAllUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorAndSaveService;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddPaymentOperationServiceImp {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final GetAllUnpaidPaymentsBelongsCreditService getUnpaidPaymentsService;
    private final UpdateAccountServiceImp updateAccountService;
    private final PaymentScheduleGeneratorAndSaveService saveService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;
    private final GetAccountsListServiceImp getAccountsListService;
    private final CreateCreditServiceImp creditService;

    @Scheduled(cron = "0 0 23 * * *")
    public List<OperationResponseDTO> execute() {
        LocalDate currentDate = LocalDate.now();

        List<Account> accounts = getAccountsListService.getAllAccounts();
        List<OperationResponseDTO> donePaymentsList = new ArrayList<>();

        if (accounts.isEmpty()) {
            throw new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_LIST_MESSAGE);
        }

        for (Account account : accounts) {

            List<PaymentSchedule> paymentSchedules = getUnpaidPaymentsService.findUnpaidPaymentByAccount(account);

            for (PaymentSchedule paymentSchedule : paymentSchedules) {

                if (paymentSchedule != null && paymentSchedule.getPaymentDate().equals(currentDate)) {
                    Card card = searchCardService.findByAccount(account);
                    Account accountAfterOperation = util.payBill(account, paymentSchedule, card);

                    if (account.getBalance().compareTo(paymentSchedule.getMonthlyPayment()) < 0) {
                        handleImmediateFine(account, paymentSchedule);
                    } else {
                        handleSuccessfulPayment(donePaymentsList, accountAfterOperation, paymentSchedule, card);
                    }
                    updateAccountService.saveUpdatedAccount(accountAfterOperation);
                    saveService.save(paymentSchedule);
                    createCardService.saveCard(card);

                }

                if (paymentSchedule != null && !paymentSchedule.getSurcharge().equals(BigDecimal.ZERO)) {
                    handleDelayedFine(paymentSchedule, account);
                }

            }
        }
        return donePaymentsList;
    }


    private void handleImmediateFine(Account account, PaymentSchedule paymentSchedule) {
        int dayOfDelay = 1;
        BigDecimal fine = util.calculateFine(account.getCredit().getInterestRate(), paymentSchedule.getMonthlyPayment(), dayOfDelay);

        paymentSchedule.setSurcharge(fine);
        account.getCredit().setFine(fine);
        creditService.saveCredit(account.getCredit());
    }

    private void handleSuccessfulPayment(List<OperationResponseDTO> donePaymentsList, Account account, PaymentSchedule paymentSchedule, Card card) {
        Operation operation = util.convertDataToOperationForPayment(account, paymentSchedule);
        Operation savedOperation = saveOperation(operation);
        donePaymentsList.add(util.convertOperationToResponseDTO(savedOperation));
    }

    private void handleDelayedFine(PaymentSchedule paymentSchedule, Account account) {
        int dayOfDelay = (int) ChronoUnit.DAYS.between(paymentSchedule.getPaymentDate(), LocalDate.now());
        BigDecimal fine = util.calculateFine(account.getCredit().getInterestRate(), paymentSchedule.getMonthlyPayment(), dayOfDelay);

        paymentSchedule.setSurcharge(paymentSchedule.getSurcharge().add(fine));
        account.getCredit().setFine(fine);
        creditService.saveCredit(account.getCredit());
        saveService.save(paymentSchedule);
    }

    public Operation saveOperation(Operation operation) {
        return repository.save(operation);
    }

}
