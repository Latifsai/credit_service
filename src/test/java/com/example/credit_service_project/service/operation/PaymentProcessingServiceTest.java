package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.generators.DelayDTOGenerator;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.account.GetAllAccountsService;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.card.CardCreateService;
import com.example.credit_service_project.service.card.CardSearchService;
import com.example.credit_service_project.service.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.credit.CreditCreateService;
import com.example.credit_service_project.service.credit.CreditSearchService;
import com.example.credit_service_project.service.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.delay.DelayService;
import com.example.credit_service_project.service.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessingServiceTest {
    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;
    @Mock
    private CheckUnpaidPaymentsBelongsCreditService getUnpaidPaymentsService;
    @Mock
    private AccountUpdateService updateAccountService;
    @Mock
    private PaymentScheduleGeneratorService saveService;
    @Mock
    private CardSearchService searchCardService;
    @Mock
    private CardCreateService createCardService;
    @Mock
    private GetAllAccountsService getAllAccountsService;
    @Mock
    private CreditCreateService creditService;
    @Mock
    private CreditOrderCreateService addCreditOrderService;
    @Mock
    private AgreementCreateService createAgreementService;
    @Mock
    private CreditSearchService creditSearchService;
    @Mock
    private DelayService delayService;
    @InjectMocks
    private PaymentProcessingService paymentProcessingService;
    @Test
    @DisplayName("Test handlePayments method")
    void handlePayments() {
        Account account = EntityCreator.getAccount();
        Credit credit = EntityCreator.getCredit();
        Card card = EntityCreator.getCard();
        PaymentSchedule paymentSchedule = EntityCreator.getPayment();
        paymentSchedule.setPaymentDate(LocalDate.now());

        when(getAllAccountsService.findAllAccounts()).thenReturn(Collections.singletonList(account));
        when(creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE)).thenReturn(credit);
        when(getUnpaidPaymentsService.findUnpaidPaymentByAccount(account)).thenReturn(Collections.singletonList(paymentSchedule));
        when(searchCardService.findByAccount(account)).thenReturn(card);
        when(util.payBill(account, paymentSchedule, card)).thenReturn(EntityCreator.getAccountAfterOperation());
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);

        List<OperationResponseDTO> result = paymentProcessingService.handlePayments();

        assertEquals(2, result.size());
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(saveService, times(1)).save(paymentSchedule);
        verify(createCardService, times(1)).saveCard(card);
    }

    @Test
    @DisplayName("Test handlePaymentsImmediateFine method")
    void handlePaymentsImmediateFine() {
        Account account = EntityCreator.getAccountForHandleFine();
        Credit credit = EntityCreator.getCredit();
        Card card = EntityCreator.getCard();
        PaymentSchedule paymentSchedule = EntityCreator.getPayment();
        paymentSchedule.setPaymentDate(LocalDate.now());

        when(getAllAccountsService.findAllAccounts()).thenReturn(Collections.singletonList(account));
        when(creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE)).thenReturn(credit);
        when(getUnpaidPaymentsService.findUnpaidPaymentByAccount(account)).thenReturn(Collections.singletonList(paymentSchedule));
        when(searchCardService.findByAccount(account)).thenReturn(card);
        when(util.payBill(account, paymentSchedule, card)).thenReturn(EntityCreator.getAccountAfterOperation());
        when(util.calculateFine(credit.getInterestRate(), paymentSchedule.getMonthlyPayment(), 1)).thenReturn(BigDecimal.valueOf(5));
        when(delayService.addNewDelay(BigDecimal.valueOf(5), account)).thenReturn(DelayDTOGenerator.getResponse());

        List<OperationResponseDTO> result = paymentProcessingService.handlePayments();

        assertEquals(0, result.size()); // No payments were made
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(saveService, times(2)).save(paymentSchedule);
        verify(createCardService, times(1)).saveCard(card);
    }

    @Test
    @DisplayName("Test handlePaymentsDelayedFine method")
    void handlePaymentsDelayedFine() {
        Account account = EntityCreator.getAccountForHandleFine();
        Credit credit = EntityCreator.getCreditForHandleDelayedFine();
        PaymentSchedule paymentSchedule = EntityCreator.getPaymentForHandleDelayedFine();


        when(getAllAccountsService.findAllAccounts()).thenReturn(Collections.singletonList(account));
        when(creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE)).thenReturn(credit);
        when(getUnpaidPaymentsService.findUnpaidPaymentByAccount(account)).thenReturn(Collections.singletonList(paymentSchedule));
        when(util.calculateFine(credit.getInterestRate(), paymentSchedule.getMonthlyPayment(), 10)).thenReturn(BigDecimal.valueOf(15));

        List<OperationResponseDTO> result = paymentProcessingService.handlePayments();

        assertEquals(0, result.size());
        verify(saveService, times(1)).save(paymentSchedule);
        verify(getAllAccountsService, times(1)).findAllAccounts();
        verify(creditSearchService, times(5)).searchCreditByAccountAndStatus(account, ACTIVE);
        verify(util, times(1)).calculateFine(credit.getInterestRate(),
                paymentSchedule.getMonthlyPayment(), 10);
    }

    @Test
    @DisplayName("Test handlePaymentsClosePaidCredit method")
    void handlePaymentsClosePaidCredit() {
        Account account = EntityCreator.getAccountForClosePaidCredit();
        Credit credit = EntityCreator.getCredit();
        Card card = EntityCreator.getCard();
        PaymentSchedule paymentSchedule = EntityCreator.getPayment();
        paymentSchedule.setPaymentDate(LocalDate.now());

        when(getAllAccountsService.findAllAccounts()).thenReturn(Collections.singletonList(account));
        when(creditSearchService.searchCreditByAccountAndStatus(account, ACTIVE)).thenReturn(credit);
        when(getUnpaidPaymentsService.findUnpaidPaymentByAccount(account))
                .thenReturn(Collections.singletonList(paymentSchedule));
        when(searchCardService.findByAccount(account)).thenReturn(card);
        when(util.payBill(account, paymentSchedule, card)).thenReturn(EntityCreator.getAccountAfterOperation());

        List<OperationResponseDTO> result = paymentProcessingService.handlePayments();

        assertEquals(2, result.size());
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(saveService, times(1)).save(paymentSchedule);
        verify(createCardService, times(1)).saveCard(card);
        verify(creditService, times(1)).saveCredit(credit);
        verify(addCreditOrderService, times(1)).saveOrder(credit.getCreditOrder());
        verify(createAgreementService, times(1)).saveAgreement(credit.getAgreement());
    }

    @Test
    @DisplayName("Test getAllAccountsService method throws NotFoundException")
    public void getAllAccountsServiceNotFoundException() {
        when(getAllAccountsService.findAllAccounts()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> paymentProcessingService.handlePayments());
    }

}