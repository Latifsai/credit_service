package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.operationDTO.AddOperationReplenishmentRequest;
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
import java.util.HashMap;
import java.util.Map;

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
                ((operation.getType().equals(MONTHLY_PAYMENT)
                        || operation.getType().equals(EARLY_REPAYMENT)
                        || operation.getType().equals(PAYMENT_WITH_FINE))
                        && request.getType().equals(REPLENISHMENT))) {

            throw new OperationException(ErrorsMessage.OPERATION_TYPE_CHANGE_MESSAGE);
        }
        return request.getType() != null;
    }

    public Account payBill(Account account, PaymentSchedule paymentSchedule, Card card) {
        if (paymentSchedule.getPaymentDate().equals(LocalDate.now())) {
            BigDecimal balance = account.getBalance().subtract(getSumToPayForPayment(paymentSchedule));
            if (balance.intValue() < 0) throw new OperationException(ErrorsMessage.NEGATIVE_BALANCE_EXCEPTION);
            // ЗДЕСЬ РЕАЛИЗОВАТЬ МЕХАНИЗМ НАЧИСЛЕНИЯ ПЕНИ
            account.setBalance(balance);
            paymentSchedule.setActualPaymentDate(LocalDate.now());
            paymentSchedule.setPaid(true);
            changerCardBalance(account, card);
        }
        return account;
    }

    public BigDecimal getSumToPayForPayment(PaymentSchedule p) {
        return p.getMainPayment().add(p.getRatePayment()).add(p.getSurcharge());
    }

    public Operation convertDataToOperationForPayment(Account account, PaymentSchedule p) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setSum(getSumToPayForPayment(p));
        operation.setType(fillType(p));
        operation.setOperationEndMark(LocalDateTime.now());
        operation.setOperationDetails(fillDetails(operation.getType(), account));
        operation.setDebit(handleDebit(operation.getType()));
        operation.setCurrency(account.getCurrency());
        return operation;
    }

    private BigDecimal getAmountForEarlyPayment(Account account) {
        return account.getUnpaidLoanDebt().add(account.getUnpaidPercentageLoanDebt());
    }
    public Account payEarlyPayment(Account account, Card card) {
        if (account.getBalance().compareTo(getAmountForEarlyPayment(account)) < 0) {
            throw new OperationException(ErrorsMessage.NEGATIVE_BALANCE_EXCEPTION);
        }

        BigDecimal balanceAfterOperation = account.getBalance().subtract(getAmountForEarlyPayment(account));

        //updated
        account.setBalance(balanceAfterOperation);
        account.setUnpaidPercentageLoanDebt(BigDecimal.ZERO);
        account.setUnpaidLoanDebt(BigDecimal.ZERO);
        account.setPercentageDebt(BigDecimal.ZERO);
        account.setLoanDebt(BigDecimal.ZERO);
        changerCardBalance(account, card);
        // change Credit datas, terminate credit and all credit`s elements
        return account;
    }
    public Operation convertDataToOperationForEarlyPayment(Account account) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setSum(getAmountForEarlyPayment(account));
        operation.setType(EARLY_REPAYMENT);
        operation.setOperationEndMark(LocalDateTime.now());
        operation.setOperationDetails(fillDetails(operation.getType(), account));
        operation.setDebit(handleDebit(operation.getType()));
        operation.setCurrency(account.getCurrency());
        return operation;
    }

    public Operation convertDataToOperationForREPLENISHMENT(AddOperationReplenishmentRequest request, Account account) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setSum(request.getSum());
        operation.setType(REPLENISHMENT);
        operation.setOperationEndMark(LocalDateTime.now());
        operation.setOperationDetails(fillDetails(operation.getType(), account));
        operation.setDebit(handleDebit(operation.getType()));
        operation.setCurrency(account.getCurrency());
        return operation;
    }

    private OperationType fillType(PaymentSchedule p) {
        if (!p.getSurcharge().equals(BigDecimal.ZERO)) {
            return PAYMENT_WITH_FINE;
        } else {
            return MONTHLY_PAYMENT;
        }
    }

    private String fillDetails(OperationType type, Account account) {
        Map<OperationType, String> operationMessages = new HashMap<>();
        operationMessages.put(OperationType.EARLY_REPAYMENT, OperationDetailsMessage.EARLY_REPAYMENT_MESSAGE);
        operationMessages.put(OperationType.MONTHLY_PAYMENT, OperationDetailsMessage.MONTHLY_PAYMENT_MESSAGE);
        operationMessages.put(OperationType.PAYMENT_WITH_FINE, OperationDetailsMessage.PAYMENT_WITH_FINE_MESSAGE);
        operationMessages.put(OperationType.REPLENISHMENT, OperationDetailsMessage.REPLENISHMENT_OPERATION_MESSAGE);

        String message = operationMessages.getOrDefault(type, "");

        if (!message.isEmpty()) {
            return message + "Account number: " + account.getAccountNumber();
        } else {
            return "";
        }
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
