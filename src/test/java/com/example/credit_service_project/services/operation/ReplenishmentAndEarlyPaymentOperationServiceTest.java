package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.card.CardCreateService;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.services.credit.CreditCreateService;
import com.example.credit_service_project.services.credit.CreditSearchService;
import com.example.credit_service_project.services.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.services.generators.DTOOperationCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.validation.exceptions.EarlyPaymentException;
import com.example.credit_service_project.validation.exceptions.OperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReplenishmentAndEarlyPaymentOperationServiceTest {

    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private CardSearchService cardSearchService;
    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;
    @Mock
    private AccountUpdateService updateAccountService;
    @Mock
    private CardCreateService updateCardService;
    @Mock
    private CheckUnpaidPaymentsBelongsCreditService checkUnpaidPaymentsBelongsCreditService;
    @Mock
    private PaymentScheduleGeneratorService saveService;
    @Mock
    private CreditCreateService addCreditService;
    @Mock
    private CreditOrderCreateService addCreditOrderService;
    @Mock
    private AgreementCreateService addAgreementService;
    @Mock
    private CreditSearchService creditSearchService;
    @InjectMocks
    private ReplenishmentAndEarlyPaymentOperationService replenishmentAndEarlyPaymentOperationService;

    @Test
    void performOperationReplenishment() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(1000), OperationType.REPLENISHMENT, "REPLENISHMENT");
        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();
        Card cardAfter = EntityCreator.getCardAfterOperationReplenishment();
        Operation operation = EntityCreator.getOperationREPLENISHMENT();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(cardSearchService.findByAccount(account)).thenReturn(card);
        when(util.changerCardBalance(account, card)).thenReturn(cardAfter); //??
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);
        when(updateCardService.saveCard(cardAfter)).thenReturn(cardAfter);
        when(util.convertPaymentsOperationRequestToOperation(request, account)).thenReturn(operation);
        when(repository.save(operation)).thenReturn(operation);
        when(util.convertOperationToResponseDTO(operation)).thenReturn(DTOOperationCreator.getOperationResponseREPLENISHMENT());

        OperationResponseDTO operationResponseDTO = replenishmentAndEarlyPaymentOperationService.performOperation(request);

        assertEquals(BigDecimal.valueOf(4000), EntityCreator.getCardAfterOperationReplenishment().getBalance());
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(updateCardService, times(1)).saveCard(cardAfter);
        verify(repository, times(1)).save(operation);
        assertNotNull(operationResponseDTO);
    }


    @Test
    void performOperationEARLY_REPAYMENT() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(1000), OperationType.EARLY_REPAYMENT, "EARLY_REPAYMENT");

        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();
        Credit credit = EntityCreator.getCredit();
        Operation operation = EntityCreator.getOperation();
        PaymentSchedule payment = EntityCreator.getPayment();
        CreditOrder order = EntityCreator.getCreditOrder();
        Agreement agreement = EntityCreator.getAgreement();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(cardSearchService.findByAccount(account)).thenReturn(card);
        when(creditSearchService.searchCreditByAccountAndStatus(account, CreditStatus.ACTIVE)).thenReturn(credit);
        when(util.payEarlyPayment(request, account, card)).thenReturn(account);
        when(checkUnpaidPaymentsBelongsCreditService.findUnpaidPaymentByAccount(account)).thenReturn(List.of(payment));
        when(saveService.save(payment)).thenReturn(payment);
        when(addCreditService.saveCredit(credit)).thenReturn(credit);
        when(addCreditOrderService.saveOrder(order)).thenReturn(order);
        when(addAgreementService.saveAgreement(agreement)).thenReturn(agreement);
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);
        when(updateCardService.saveCard(card)).thenReturn(card);
        when(util.convertPaymentsOperationRequestToOperation(request, account)).thenReturn(operation);
        when(repository.save(operation)).thenReturn(operation);
        when(util.convertOperationToResponseDTO(operation)).thenReturn(DTOOperationCreator.getOperationResponseDTO());

        OperationResponseDTO operationResponseDTO = replenishmentAndEarlyPaymentOperationService.performOperation(request);
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(updateCardService, times(1)).saveCard(card);
        verify(repository, times(1)).save(operation);
        assertNotNull(operationResponseDTO);
    }

    @Test
    void performOperation_OperationException() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(-1000), OperationType.EARLY_REPAYMENT, "EARLY_REPAYMENT");
        assertThrows(OperationException.class, () -> replenishmentAndEarlyPaymentOperationService.performOperation(request));
    }

    @Test
    void performOperationEarlyPaymentException() {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(1000), OperationType.EARLY_REPAYMENT, "EARLY_REPAYMENT");
        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();
        Credit credit = EntityCreator.getCredit();
        credit.getCreditOrder().setProduct(EntityCreator.getProductFalseEP());

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(cardSearchService.findByAccount(account)).thenReturn(card);
        when(creditSearchService.searchCreditByAccountAndStatus(account, CreditStatus.ACTIVE)).thenReturn(credit);

        assertThrows(EarlyPaymentException.class, () -> replenishmentAndEarlyPaymentOperationService.performOperation(request));

    }

}