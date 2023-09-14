package com.example.credit_service_project.services.paymentSchedule;

import com.example.credit_service_project.dto.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetNearestPaymentServiceTest {
    @Mock
    private PaymentScheduleUtil util;
    @Mock
    private AccountSearchService accountSearchService;
    @InjectMocks
    private GetNearestPaymentService service;


    @DisplayName(value = "Test get Nearest Payment")
    @Test
    public void testGetNearestPayments() {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);
        Account account = EntityCreator.getAccount();
        PaymentSchedule payment = EntityCreator.getPayment();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(util.getNearestPaymentSchedule(account)).thenReturn(payment);
        when(util.convertEntityToPaymentResponse(payment)).thenReturn(PaymentDTOGenerator.getPaymentResponseDTO());

        assertEquals(PaymentDTOGenerator.getPaymentResponseDTO(), service.getNearestPayment(request));
    }

    @Test
    public void testGetBelongsPaymentsNotFoundException() {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> service.getNearestPayment(request));
    }
}