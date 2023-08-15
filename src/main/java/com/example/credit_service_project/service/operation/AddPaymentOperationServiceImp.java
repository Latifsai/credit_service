package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.GetAccountsListServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import com.example.credit_service_project.validation.exceptions.CardNotFoundException;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.service.utils.PaymentCheckJob;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddPaymentOperationServiceImp implements OperationService<List<OperationResponseDTO>, AddOperationPaymentRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final GetNearestPaymentServiceImp getNearestPaymentService;
    private final UpdateAccountServiceImp updateAccountService;
    private final AddPaymentScheduleServiceImp addPaymentScheduleService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;
    private final GetAccountsListServiceImp getAccountsListService;

    @Override
    public List<OperationResponseDTO> execute(AddOperationPaymentRequest request) {
        List<Account> accounts = getAccountsListService.getAllAccounts();
        List<OperationResponseDTO> donePaymentsList = new ArrayList<>();

        if (accounts.isEmpty()) {
            throw new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_LIST_MESSAGE);
        }

        for (Account account : accounts) {
            PaymentSchedule paymentSchedule = getNearestPaymentService.getNearestPayment(account);

            if (paymentSchedule != null && paymentSchedule.getPaymentDate().equals(LocalDate.now())) {
                Card card = searchCardService.findByAccount(account)
                        .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));

                Account accountAfterOperation = util.payBill(account, paymentSchedule, card);

                updateAccountService.saveUpdatedAccount(accountAfterOperation);
                addPaymentScheduleService.savePayment(paymentSchedule);
                createCardService.saveCard(card);

                Operation operation = util.convertAddOperationPaymentRequestToOperation(request, account, paymentSchedule);
                Operation savedOperation = saveOperation(operation);

                scheduleDailyPaymentCheck(request);
                donePaymentsList.add(util.convertOperationToResponseDTO(savedOperation));
            }
        }
        return donePaymentsList;
    }

    public Operation saveOperation(Operation operation) {
        return repository.save(operation);
    }

    public void scheduleDailyPaymentCheck(AddOperationPaymentRequest request) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = JobBuilder.newJob(PaymentCheckJob.class)
                    .withIdentity("paymentCheckJob", "paymentGroup")
                    .storeDurably(true)
                    .build();

            job.getJobDataMap().put("request", request);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("paymentCheckTrigger", "paymentGroup")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(12, 0))
                    .forJob(job)
                    .build();

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
