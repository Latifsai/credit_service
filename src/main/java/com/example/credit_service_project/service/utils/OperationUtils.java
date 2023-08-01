package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.OperationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class OperationUtils {

    public Operation convertAddRequestFunctionalToOperation(AddOperationRequestSpendingOrReplenishment request, Account account) {
        var operation = new Operation();
        operation.setAccount(account);

        operation.setSum(request.getSum());
        operation.setType(request.getType());
        operation.setOperationEndMark(LocalDate.now());
        operation.setOperationDetails(request.getOperationDetails());
        operation.setDebit(handleDebit(operation.getType()));
        operation.setCurrency(operation.getAccount().getCurrency());
        return operation;
    }

    public Account changeBalance(Account account, Operation operation) {
        BigDecimal balance;
        if (operation.isDebit()) {
             balance = account.getBalance().subtract(operation.getSum());
            if (balance.intValue() < 0) {
                throw new OperationException(ErrorsMessage.NEGATIVE_BALANCE_EXCEPTION);
            }
        } else {
            balance = account.getBalance().add(operation.getSum());
        }
        account.setBalance(balance);
        return account;
    }

    public AddOperationResponse convertOperationToAddResponse(Operation operation) {
        return new AddOperationResponse(
                operation.getAccount().getAccountNumber(),
                operation.getAccount().getBalance(),
                operation.getSum(),
                operation.getType(),
                operation.getOperationEndMark(),
                operation.getOperationDetails(),
                operation.getCurrency());
    }

    private boolean handleDebit(OperationType type) {
        return !type.equals(OperationType.REPLENISHMENT);
    }

    public SearchOperationResponse convertOperationToSearchResponse(Operation operation) {
        return new SearchOperationResponse(
                operation.getId(),
                operation.getAccount().getAccountNumber(),
                operation.getSum(),
                operation.getType(),
                operation.getOperationEndMark(),
                operation.isDebit(),
                operation.getOperationDetails(),
                operation.getCurrency());
    }

    public OperationResponseDTO convertOperationToResponseDTO(Operation operation) {
        return new OperationResponseDTO(
                operation.getId(),
                operation.getSum(),
                operation.getType(),
                operation.getOperationEndMark(),
                operation.getOperationDetails(),
                operation.getCurrency()
        );
    }


    public Operation updateOperation(Operation operation, UpdateOperationsRequest request) {
        if (request.getOperationDetails() != null && !request.getOperationDetails().trim().isEmpty()){
            operation.setOperationDetails(request.getOperationDetails());
        }
        if (checkType(operation, request)) operation.setType(request.getType());
        return operation;
    }

    private boolean checkType(Operation operation, UpdateOperationsRequest request) {
        if (operation.getType().equals(OperationType.REPLENISHMENT)
                && !request.getType().equals(OperationType.REPLENISHMENT)) {
            // поинтересотавться по поводу валидности и реализации метода
            throw new OperationException(ErrorsMessage.OPERATION_TYPE_CHANGE_MESSAGE);
        }
        return request.getType() != null;
    }

    public UpdateOperationsResponse convertUpdatedOperationToUpdateResponse(Operation operation) {
        return new UpdateOperationsResponse(
                operation.getId(),
                operation.getOperationEndMark(),
                operation.isDebit(),
                operation.getSum(),
                operation.getType(),
                operation.getCurrency(),
                operation.getOperationDetails()
        );
    }
}
