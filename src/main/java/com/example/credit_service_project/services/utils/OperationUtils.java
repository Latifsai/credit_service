package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.credit_service_project.entity.enums.OperationType.*;

@Service
public class OperationUtils {

    public Card changerCardBalance(Account account, Card card) {
        if (!account.getBalance().equals(card.getBalance())) {
            card.setBalance(account.getBalance());
        }
        return card;
    }

    private boolean handleDebit(OperationType type) {
        return !type.equals(REPLENISHMENT);
    }

    public Operation updateOperation(Operation operation, UpdateOperationsRequest request) {
        if (request.getOperationDetails() != null && !request.getOperationDetails().trim().isEmpty()) {
            operation.setOperationDetails(request.getOperationDetails());
        }
        if (request.getType() != null) {
            checkType(operation, request);
        }
        return operation;
    }

    private void checkType(Operation operation, UpdateOperationsRequest request) {
        if ((operation.getType().equals(REPLENISHMENT) && !request.getType().equals(REPLENISHMENT)) ||
                ((operation.getType().equals(MONTHLY_PAYMENT)
                        || operation.getType().equals(EARLY_REPAYMENT)
                        || operation.getType().equals(PAYMENT_WITH_FINE))
                        && request.getType().equals(REPLENISHMENT))) {

            throw new OperationException(ErrorsMessage.OPERATION_TYPE_CHANGE_MESSAGE);
        } else {
            operation.setType(request.getType());
        }
    }

    public BigDecimal getSumToPayForPayment(PaymentSchedule p) {
        return p.getMonthlyPayment().add(p.getSurcharge());
    }

    public Account payBill(Account account, PaymentSchedule paymentSchedule, Card card) {
        if (paymentSchedule.getPaymentDate().equals(LocalDate.now())) {
            BigDecimal balance = account.getBalance().subtract(getSumToPayForPayment(paymentSchedule));

            account.setBalance(balance);
            account.setUnpaidCreditSum(account.getUnpaidCreditSum().subtract(getSumToPayForPayment(paymentSchedule)));
            //substract and set new setUnpaidCreditSum
            paymentSchedule.setActualPaymentDate(LocalDate.now());
            paymentSchedule.setPaid(true);
            paymentSchedule.setSurcharge(BigDecimal.ZERO);
            changerCardBalance(account, card);
        }
        return account;
    }

    public BigDecimal calculateFine(BigDecimal interestRate, BigDecimal payment, int dayOfDelay) {
        return payment.multiply((interestRate.divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP)))
                .multiply(BigDecimal.valueOf(dayOfDelay / 365));
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
        return account.getUnpaidCreditSum();
    }

    public Account payEarlyPayment(PaymentsOperationRequest request, Account account, Card card) {

        if (!request.getSum().equals(getAmountForEarlyPayment(account))) {
            throw new OperationException(ErrorsMessage.INSUFFICIENT_AMOUNT_MESSAGE);
        }

        if (account.getBalance().compareTo(request.getSum()) < 0) {
            throw new OperationException(ErrorsMessage.NEGATIVE_BALANCE_EXCEPTION);
        }

        BigDecimal balanceAfterOperation = account.getBalance().subtract(request.getSum());

        //updated
        account.setBalance(balanceAfterOperation);
        account.setUnpaidCreditSum(BigDecimal.ZERO);
        account.setPercentageDebt(BigDecimal.ZERO);
        account.setLoanDebt(BigDecimal.ZERO);
        changerCardBalance(account, card);
        // change Credit datas, terminate credit and all credit`s element
        return account;
    }

    public Operation convertPaymentsOperationRequestToOperation(PaymentsOperationRequest request, Account account) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setSum(request.getSum());
        operation.setType(request.getType());
        operation.setOperationEndMark(LocalDateTime.now());
        operation.setOperationDetails(request.getOperationDetails());
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
        return OperationResponseDTO
                .builder()
                .accountNumber(operation.getAccount().getAccountNumber())
                .balance(operation.getAccount().getBalance())
                .id(operation.getId())
                .sum(operation.getSum())
                .isDebit(operation.isDebit())
                .type(operation.getType())
                .operationEndMark(operation.getOperationEndMark())
                .operationDetails(operation.getOperationDetails())
                .currency(operation.getCurrency())
                .build();
    }
}
