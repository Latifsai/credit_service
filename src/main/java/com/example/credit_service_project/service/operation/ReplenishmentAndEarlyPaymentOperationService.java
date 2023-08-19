package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
            account = util.payEarlyPayment(request, account, card);
        }

        updateAccountService.saveUpdatedAccount(account);
        updateCardService.saveCard(card);
        Operation operation = util.convertPaymentsOperationRequestToOperation(request, account);
        Operation savedOperation = repository.save(operation);
        return util.convertOperationToResponseDTO(savedOperation);
    }
}