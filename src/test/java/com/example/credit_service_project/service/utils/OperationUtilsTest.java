package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static com.example.credit_service_project.entity.enums.OperationType.PAYMENT_WITH_FINE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OperationUtilsTest {

    @InjectMocks
    private OperationUtils utils;

    private Operation operation;
    private Account account;
    private PaymentSchedule payment;
    private Card card;

    @BeforeEach
    @DisplayName("Test setUp method")
    public void setUp() {
        operation = EntityCreator.getOperation();
        account = EntityCreator.getAccount();
        payment = EntityCreator.getPayment();
        card = EntityCreator.getCard();
    }

    @Test
    @DisplayName("Test changerCardBalance method")
    void changerCardBalance() {
        assertNotNull(utils.changerCardBalance(account, card));
    }

    @Test
    @DisplayName("Test changerCardBalance method")
    void changerCardBalanceIfCardBalanceMoreThanAccountBalance() {
        card.setBalance(BigDecimal.TEN);
        account.setBalance(BigDecimal.ZERO);
        assertNotNull(utils.changerCardBalance(account, card));
    }

    @Test
    @DisplayName("Test updateOperation method")
    void updateOperation() {
        UpdateOperationsRequest request = new UpdateOperationsRequest(UUID.randomUUID(), PAYMENT_WITH_FINE, null);
        assertNotNull(utils.updateOperation(operation, request));
    }

    @Test
    @DisplayName("Test updateOperation method_2")
    void updateOperation_2() {
        UpdateOperationsRequest request = new UpdateOperationsRequest(UUID.randomUUID(), PAYMENT_WITH_FINE, "payment with fine");
        assertNotNull(utils.updateOperation(operation, request));
    }

    @Test
    @DisplayName("Test getSumToPayForPayment method")
    void getSumToPayForPayment() {
        assertEquals(BigDecimal.valueOf(300), utils.getSumToPayForPayment(payment));
    }

    @Test
    @DisplayName("Test payBill method")
    void payBill() {
        Account accountAfterOperation = EntityCreator.getAccountAfterOperation();

        assertEquals(accountAfterOperation, utils.payBill(account, payment, card));
    }

    @Test
    @DisplayName("Test calculateFine method")
    void calculateFine() {
        BigDecimal result = utils.calculateFine(BigDecimal.valueOf(5.33), BigDecimal.valueOf(300), 10);
        assertEquals(new BigDecimal("0.44"), result);
    }

    @Test
    @DisplayName("Test convertDataToOperationForPayment method")
    void convertDataToOperationForPayment() {
        assertNotNull(utils.convertDataToOperationForPayment(account, payment));
    }

    @Test
    @DisplayName("Test payEarlyPayment method")
    void payEarlyPayment() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.randomUUID(), null,
                BigDecimal.valueOf(0), OperationType.EARLY_REPAYMENT, "EP");
        account.setBalance(BigDecimal.valueOf(2000));

        Account afterEarlyPayment = EntityCreator.getAccountForClosePaidCredit();
        assertEquals(afterEarlyPayment, utils.payEarlyPayment(request, account, card));
    }

    @Test
    @DisplayName("Test payEarlyPayment method throws OperationException")
    void payEarlyPaymentOperationException() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.randomUUID(), null,
                BigDecimal.valueOf(200), OperationType.EARLY_REPAYMENT, "EP");

        assertThrows(OperationException.class, () -> utils.payEarlyPayment(request, account, card));
    }

    @Test
    @DisplayName("Test convertPaymentsOperationRequestToOperation method")
    void convertPaymentsOperationRequestToOperation() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"), null, BigDecimal.valueOf(300),
                OperationType.REPLENISHMENT, "EP");

        Operation result = utils.convertPaymentsOperationRequestToOperation(request, account);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test convertOperationToResponseDTO method")
    void convertOperationToResponseDTO() {
        operation.setAccount(account);
        assertNotNull(utils.convertOperationToResponseDTO(operation));
    }
}