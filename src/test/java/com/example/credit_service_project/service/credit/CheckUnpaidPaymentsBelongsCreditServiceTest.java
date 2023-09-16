package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.paymentSchedule.GetBelongsToAccountPaymentsService;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckUnpaidPaymentsBelongsCreditServiceTest {

    @Mock
    private CreditRepository repository;
    @Mock
    private GetBelongsToAccountPaymentsService belongsToTheAccountPaymentsListService;
    @Mock
    private PaymentScheduleUtil util;
    @InjectMocks
    private CheckUnpaidPaymentsBelongsCreditService checkUnpaidPaymentsBelongsCreditService;

    private final UUID creditID = UUID.randomUUID();

    @Test
    @DisplayName("Test check unpaid payments belongs credit method")
    void checkUnpaidPaymentsBelongsCredit() {
        Credit credit = EntityCreator.getCredit();
        Account account = EntityCreator.getAccountForClosePaidCredit();

        List<PaymentSchedule> unpaidPayments = List.of(EntityCreator.getPayment());

        when(repository.findById(creditID)).thenReturn(Optional.of(credit));
        when(belongsToTheAccountPaymentsListService.findAllByAccount(account)).thenReturn(unpaidPayments);
        when(util.convertEntityToPaymentResponse(any(PaymentSchedule.class))).thenReturn(PaymentDTOGenerator.getPaymentResponseDTO());
        List<PaymentResponseDTO> result = checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(creditID);

        assertEquals(1, result.size());
        verify(repository, times(1)).findById(creditID);
        verify(belongsToTheAccountPaymentsListService, times(1)).findAllByAccount(account);
        verify(util, times(unpaidPayments.size())).convertEntityToPaymentResponse(any(PaymentSchedule.class));
    }

    @Test
    @DisplayName("Test check unpaid payments belongs credit method throws NotFoundException")
    void checkUnpaidPaymentsBelongsCreditNotFoundException() {
        when(repository.findById(creditID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(creditID));
        verify(repository, times(1)).findById(creditID);
    }

    @Test
    @DisplayName("Test find unpaid payment by account method")
    void findUnpaidPaymentByAccount() {
        List<PaymentSchedule> list = List.of(EntityCreator.getPayment());
        Account account = EntityCreator.getAccount();

        when(belongsToTheAccountPaymentsListService.findAllByAccount(account)).thenReturn(list);

        List<PaymentSchedule> result = checkUnpaidPaymentsBelongsCreditService.findUnpaidPaymentByAccount(account);

        assertEquals(list.size(), result.size());
        verify(belongsToTheAccountPaymentsListService, times(1)).findAllByAccount(account);
    }
}