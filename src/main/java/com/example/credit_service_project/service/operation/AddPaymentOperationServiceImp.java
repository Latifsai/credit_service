package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddPaymentOperationServiceImp implements OperationService<OperationResponseDTO, AddOperationPaymentRequest> {

    private final OperationRepository repository;
    private final OperationUtils util;
    private final GetNearestPaymentServiceImp getNearestPaymentService;
    private final SearchAccountsServiceImp searchAccountsService;
    private final UpdateAccountServiceImp updateAccountService;
    private final AddPaymentScheduleServiceImp addPaymentScheduleService;

    @Override
    public OperationResponseDTO execute(AddOperationPaymentRequest request) {
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (account.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);

        PaymentSchedule paymentSchedule = getNearestPaymentService.getNearestPayment(account.get());
        Operation operation = util.convertAddOperationPaymentRequestToOperation(request, account.get(), paymentSchedule);
        Account accountAfterOperation = util.payBill(account.get(), paymentSchedule);

        updateAccountService.saveUpdatedAccount(accountAfterOperation);
        addPaymentScheduleService.savePayment(paymentSchedule);
        repository.save(operation);

        return util.convertOperationToResponseDTO(operation);
    }
}
