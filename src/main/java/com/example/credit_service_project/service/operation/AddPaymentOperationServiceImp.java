package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.AccountNotFoundException;
import com.example.credit_service_project.service.errors.exceptions.CardNotFoundException;
import com.example.credit_service_project.service.paymentSchedule.AddPaymentScheduleServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardService;

    @Override
    public OperationResponseDTO execute(AddOperationPaymentRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        Card card = searchCardService.findCardById(request.getCardID())
                .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));

        PaymentSchedule paymentSchedule = getNearestPaymentService.getNearestPayment(account);
        Operation operation = util.convertAddOperationPaymentRequestToOperation(request, account, paymentSchedule);

        Account accountAfterOperation = util.payBill(account, paymentSchedule, card);

        updateAccountService.saveUpdatedAccount(accountAfterOperation);
        addPaymentScheduleService.savePayment(paymentSchedule);
        createCardService.saveCard(card);

        Operation savedOperation = saveOperation(operation);
        return util.convertOperationToResponseDTO(savedOperation);
    }

    public Operation saveOperation(Operation operation) {
        return repository.save(operation);
    }
}
