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
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddPaymentOperationServiceImp {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final GetNearestPaymentServiceImp getNearestPaymentService;
    private final UpdateAccountServiceImp updateAccountService;
    private final AddPaymentScheduleServiceImp addPaymentScheduleService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;
    private final GetAccountsListServiceImp getAccountsListService;


    @Scheduled(cron = "0 0 8,13,21 * * *")
        public List<OperationResponseDTO> execute() {

        List<Account> accounts = getAccountsListService.getAllAccounts();
        List<OperationResponseDTO> donePaymentsList = new ArrayList<>();

        if (accounts.isEmpty()) {
            throw new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_LIST_MESSAGE);
        }

        for (Account account : accounts) {
            PaymentSchedule paymentSchedule = getNearestPaymentService.getNearestPayment(account);

            if (paymentSchedule != null && paymentSchedule.getPaymentDate().equals(LocalDate.now())) {
                Card card = searchCardService.findByAccount(account);

                Account accountAfterOperation = util.payBill(account, paymentSchedule, card);

                // сумма долга и процентная сумма тоже должны изменяться
                updateAccountService.saveUpdatedAccount(accountAfterOperation);
                addPaymentScheduleService.savePayment(paymentSchedule);
                createCardService.saveCard(card);

                Operation operation = util.convertDataToOperationForPayment(account, paymentSchedule);
                Operation savedOperation = saveOperation(operation);


                donePaymentsList.add(util.convertOperationToResponseDTO(savedOperation));
            }
        }
        return donePaymentsList;
    }

    public Operation saveOperation(Operation operation) {
        return repository.save(operation);
    }

}
