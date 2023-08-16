package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationEarlyPaymentRequest;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EarlyPaymentOperationService implements OperationService<OperationResponseDTO, AddOperationEarlyPaymentRequest> {

    private final SearchAccountsServiceImp searchAccountsService;
    private final SearchCardServiceImp cardSearchService;
    private final OperationRepository repository;
    private final OperationUtils util;
    private final UpdateAccountServiceImp updateAccountService;
    private final CreateCardServiceImp updateCardService;
    @Override
    public OperationResponseDTO execute(AddOperationEarlyPaymentRequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getNumber());

        Card card = cardSearchService.findByAccount(account);

        Account accountAfterOperation = util.payEarlyPayment(account, card);

        updateAccountService.saveUpdatedAccount(accountAfterOperation);
        updateCardService.saveCard(card);

        Operation operation = util.convertDataToOperationForEarlyPayment(account);
        Operation savedOperation = repository.save(operation);
        return util.convertOperationToResponseDTO(savedOperation);
    }
}
