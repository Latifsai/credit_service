package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.services.generators.DTOOperationCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationUtilsTest {

    @Mock
    private OperationUtils utils;

    private Operation operation;
    private Account account;
    private PaymentSchedule payment;
    private Card card;

    @BeforeEach
    public void setUp() {
        operation = EntityCreator.getOperation();
        account = EntityCreator.getAccount();
        payment = EntityCreator.getPayment();
        card = EntityCreator.getCard();
    }

    @Test
    void changerCardBalance() {
        Card updatedCard = EntityCreator.getUpdatedCard();

        when(utils.changerCardBalance(account, card)).thenReturn(updatedCard);
        assertEquals(updatedCard, utils.changerCardBalance(account, card));
    }

    @Test
    void updateOperation() {
        UpdateOperationsRequest request = new UpdateOperationsRequest(UUID.randomUUID(),
                OperationType.PAYMENT_WITH_FINE, null);

        Operation updatedOperation = EntityCreator.getUpdatedOperation();

        when(utils.updateOperation(operation, request)).thenReturn(updatedOperation);
        assertEquals(updatedOperation, utils.updateOperation(operation, request));
    }

    @Test
    void getSumToPayForPayment() {
        when(utils.getSumToPayForPayment(payment)).thenReturn(BigDecimal.valueOf(300));
        assertEquals(BigDecimal.valueOf(300), utils.getSumToPayForPayment(payment));
    }

    @Test
    void payBill() {
        Account accountAfterOperation = EntityCreator.getAccountAfterOperation();

        when(utils.payBill(account, payment, card)).thenReturn(accountAfterOperation);
        assertEquals(accountAfterOperation, utils.payBill(account, payment, card));
    }

    @Test
    void calculateFine() {
        when(utils.calculateFine(BigDecimal.TEN, BigDecimal.valueOf(300), 5)).thenReturn(new BigDecimal("0.20"));
        BigDecimal result = utils.calculateFine(BigDecimal.TEN, BigDecimal.valueOf(300), 5);
        assertEquals(new BigDecimal("0.20"), result);
    }

    @Test
    void convertDataToOperationForPayment() {
        when(utils.convertDataToOperationForPayment(account, payment)).thenReturn(operation);
        assertEquals(operation, utils.convertDataToOperationForPayment(account, payment));
    }

    @Test
    void payEarlyPayment() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.randomUUID(), null, BigDecimal.valueOf(300),
                OperationType.EARLY_REPAYMENT, "EP");

        Account afterEarlyPayment = EntityCreator.getAccountForClosePaidCredit();
        when(utils.payEarlyPayment(request, account, card)).thenReturn(afterEarlyPayment);
        assertEquals(afterEarlyPayment, utils.payEarlyPayment(request, account, card));
    }

    @Test
    void convertPaymentsOperationRequestToOperation() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"), null, BigDecimal.valueOf(300),
                OperationType.REPLENISHMENT, "EP");

        when(utils.convertPaymentsOperationRequestToOperation(request, account)).thenReturn(operation);

        Operation result = utils.convertPaymentsOperationRequestToOperation(request, account);
        assertEquals(operation, result);
    }

    @Test
    void convertOperationToResponseDTO() {
        OperationResponseDTO response = DTOOperationCreator.getOperationResponseDTO();
        when(utils.convertOperationToResponseDTO(operation)).thenReturn(response);
        assertEquals(response, utils.convertOperationToResponseDTO(operation));
    }
}