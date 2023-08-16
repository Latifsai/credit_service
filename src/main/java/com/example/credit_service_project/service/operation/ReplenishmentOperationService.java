package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationReplenishmentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
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

@Service
@RequiredArgsConstructor
public class ReplenishmentOperationService implements OperationService<OperationResponseDTO, AddOperationReplenishmentRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchCardServiceImp cardSearchService;
    private final OperationRepository repository;
    private final OperationUtils util;
    private final UpdateAccountServiceImp updateAccountService;
    private final CreateCardServiceImp updateCardService;


    @Override
    public OperationResponseDTO execute(AddOperationReplenishmentRequest request) {

        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getNumber());
        Card card = cardSearchService.findByAccount(account);

        if (request.getSum().intValueExact() < 0) {
            throw new OperationException(ErrorsMessage.NEGATIVE_SUM_EXCEPTION);
        }
        BigDecimal newBalance = account.getBalance().add(request.getSum());
        account.setBalance(newBalance);
        Card updatedCard = util.changerCardBalance(account, card);

        updateAccountService.saveUpdatedAccount(account);
        updateCardService.saveCard(updatedCard);

        Operation operation = util.convertDataToOperationForREPLENISHMENT(account);
        repository.save(operation);
        return util.convertOperationToResponseDTO(operation);
    }
}
