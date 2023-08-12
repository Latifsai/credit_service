package com.example.credit_service_project.serviceTest.paymentScheduleTest;

import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentServiceImp;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import com.example.credit_service_project.serviceTest.generators.DTOPaymentCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetNearestPaymentServiceImpTest {
    @Mock
    private PaymentScheduleUtil util;
    @Mock
    private SearchAccountsServiceImp searchAccountsService;
    @InjectMocks
    private GetNearestPaymentServiceImp service;


    @Test
    public void testGetBelongsPaymentsSuccess() {
        var request = DTOPaymentCreator.getPaymentsBelongsToAccountRequest();
        var account = EntityCreator.getAccount();
        var payment = EntityCreator.getPayment();

        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.getNearestPaymentSchedule(account)).thenReturn(EntityCreator.getPayment());

        when(util.convertEntityToPaymentResponse(payment))
                .thenReturn(DTOPaymentCreator.getPaymentResponseDTO());

        assertEquals(DTOPaymentCreator.getPaymentResponseDTO(), service.execute(request));
    }

    @Test
    public void testGetBelongsPaymentsNotFoundException() {
        var request = DTOPaymentCreator.getPaymentsBelongsToAccountRequest();


        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));

    }
}