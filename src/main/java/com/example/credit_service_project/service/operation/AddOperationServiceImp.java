package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.DTO.operationDTO.AddOperationRequestSpendingOrReplenishment;
import com.example.credit_service_project.DTO.operationDTO.AddOperationResponse;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.OperationService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.OperationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AddOperationServiceImp implements OperationService<AddOperationResponse, AddOperationRequestSpendingOrReplenishment> {

    private final OperationRepository repository;
    private final AccountRepository accountRepository;
    private final OperationUtils util;

    @Override
    public AddOperationResponse execute(AddOperationRequestSpendingOrReplenishment request){
        var account = accountRepository.findByAccountNumber(request.getAccountNumber());
        if (account.isPresent()) {
            var operation = util.convertAddRequestFunctionalToOperation(request, account.get());
            repository.save(operation);
            var accountAfterOperation = util.changeBalance(account.get(), operation);
            accountRepository.save(accountAfterOperation); // будет ли обновлятьтся??
            return util.convertOperationToAddResponse(operation);
        }
        throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);
        // реализовать добаление и убавление баланса
        // если  кредит +
        // если дебит -

    }
}
