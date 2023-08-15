package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.operationDTO.AddOperationPaymentRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.credit_service_project.entity.enums.OperationType.*;

@Component
public class OperationUtils {

    public Card changerCardBalance(Account account, Card card) {
        if (!account.getBalance().equals(card.getBalance())) {
            card.setBalance(account.getBalance());
        }
        return card;
    }

    private boolean handleDebit(OperationType type) {
        if (type.equals(REPLENISHMENT)) {
            return false;
        } else {
            return true;
        }
    }

    public Operation updateOperation(Operation operation, UpdateOperationsRequest request) {
        if (request.getOperationDetails() != null && !request.getOperationDetails().trim().isEmpty()) {
            operation.setOperationDetails(request.getOperationDetails());
        }
        if (checkType(operation, request)) operation.setType(request.getType());
        return operation;
    }

    private boolean checkType(Operation operation, UpdateOperationsRequest request) {
        if ((operation.getType().equals(REPLENISHMENT) && !request.getType().equals(REPLENISHMENT)) ||
                ((operation.getType().equals(SPENDING)
                        || operation.getType().equals(MONTHLY_PAYMENT)
                        || operation.getType().equals(EARLY_REPAYMENT)
                        || operation.getType().equals(PAYMENT_WITH_FINE))
                        && request.getType().equals(REPLENISHMENT))) {

            throw new OperationException(ErrorsMessage.OPERATION_TYPE_CHANGE_MESSAGE);
        }
        return request.getType() != null;
    }

    public Account payBill(Account account, PaymentSchedule paymentSchedule, Card card) {
        if (paymentSchedule.getPaymentDate().equals(LocalDate.now())) {
            BigDecimal balance = account.getBalance().subtract(getSumToPay(paymentSchedule));
            if (balance.intValue() < 0) throw new OperationException(ErrorsMessage.NEGATIVE_BALANCE_EXCEPTION);

            account.setBalance(balance);
            paymentSchedule.setActualPaymentDate(LocalDate.now());
            paymentSchedule.setPaid(true);
            changerCardBalance(account, card);
        }
        return account;
    }

    public BigDecimal getSumToPay(PaymentSchedule p) {
        return p.getMainPayment().add(p.getRatePayment()).add(p.getSurcharge());
    }

    public Operation convertAddOperationPaymentRequestToOperation(AddOperationPaymentRequest request, Account account, PaymentSchedule p) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setSum(getSumToPay(p));
        operation.setType(request.getType());
        operation.setOperationEndMark(LocalDateTime.now());
        operation.setOperationDetails(request.getOperationDetails());
        operation.setDebit(handleDebit(operation.getType()));
        operation.setCurrency(account.getCurrency());
        return operation;
    }

    public OperationResponseDTO convertOperationToResponseDTO(Operation operation) {
        return new OperationResponseDTO(
                operation.getAccount().getAccountNumber(),
                operation.getAccount().getBalance(),
                operation.getId(),
                operation.getSum(),
                operation.isDebit(),
                operation.getType(),
                operation.getOperationEndMark(),
                operation.getOperationDetails(),
                operation.getCurrency()
        );
    }

}
