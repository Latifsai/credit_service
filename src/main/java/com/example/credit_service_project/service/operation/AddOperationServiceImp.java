package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationRequestSpendingOrReplenishment;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.card.UpdateCardServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddOperationServiceImp implements OperationService<OperationResponseDTO, AddOperationRequestSpendingOrReplenishment> {

    private final OperationRepository repository;
    private final OperationUtils util;

    private final UpdateAccountServiceImp updateAccountService;
    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchCardServiceImp searchCardService;
    private final UpdateCardServiceImp updateCardService;



    @Override
    public OperationResponseDTO execute(AddOperationRequestSpendingOrReplenishment request){
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (account.isPresent()) {
            Operation operation = util.convertAddRequestFunctionalToOperation(request, account.get());

            account.get().getOperations().add(operation);
            repository.save(operation);

            Account accountAfterOperation = util.changeAccountBalance(account.get(), operation);
            updateAccountService.saveUpdatedAccount(accountAfterOperation);

            Optional<Card> card = searchCardService.findCardByIdAndNumber(request.getCardID(), request.getCardNumber());
            if (card.isPresent()) {
                Card updatedCard = util.changerCardBalance(account.get(), card.get());
                updateCardService.updateCard(updatedCard);
            }

            return util.convertOperationToResponseDTO(operation);
        }
        throw new NotFoundException(ErrorsMessage.UNABLE_TO_ADD_OPERATION_MESSAGE);


    }
}
