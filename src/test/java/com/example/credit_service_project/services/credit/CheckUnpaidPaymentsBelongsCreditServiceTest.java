package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repositories.CreditRepository;
import com.example.credit_service_project.services.generators.DTOPaymentCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.paymentSchedule.GetBelongsToAccountPaymentsService;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
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

    @Test
    void checkUnpaidPaymentsBelongsCredit() {
        UUID creditID = UUID.randomUUID();
        Credit credit = EntityCreator.getCredit();
        Account account = EntityCreator.getAccountForClosePaidCredit();

        List<PaymentSchedule> unpaidPayments = List.of(EntityCreator.getPayment());

        when(repository.findById(creditID)).thenReturn(Optional.of(credit));
        when(belongsToTheAccountPaymentsListService.findAllByAccount(account)).thenReturn(unpaidPayments);
        when(util.convertEntityToPaymentResponse(any(PaymentSchedule.class))).thenReturn(DTOPaymentCreator.getPaymentResponseDTO());
        List<PaymentResponseDTO> result = checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(creditID);

        assertEquals(1, result.size());
        verify(repository, times(1)).findById(creditID);
        verify(util, times(unpaidPayments.size())).convertEntityToPaymentResponse(any(PaymentSchedule.class));
    }

    @Test
    void checkUnpaidPaymentsBelongsCreditNotFoundException() {
        UUID creditID = UUID.randomUUID();

        when(repository.findById(creditID)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->  checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(creditID));

        verify(repository, times(1)).findById(creditID);
    }

    @Test
    void findUnpaidPaymentByAccount() {
        List<PaymentSchedule> list = List.of(EntityCreator.getPayment());
        Account account = EntityCreator.getAccount();

        when(belongsToTheAccountPaymentsListService.findAllByAccount(account)).thenReturn(list);

        List<PaymentSchedule> result = checkUnpaidPaymentsBelongsCreditService.findUnpaidPaymentByAccount(account);

        assertEquals(list.size(), result.size());
        verify(belongsToTheAccountPaymentsListService,times(1)).findAllByAccount(account);
    }
}