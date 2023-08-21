package com.example.credit_service_project.serviceTest.paymentScheduleTest;

import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
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
class AddPaymentScheduleServiceImpTest {
    @Mock
    private PaymentScheduleRepository repository;
    @Mock
    private PaymentScheduleUtil util;
    @Mock
    private SearchAccountsServiceImp searchAccountsService;
    @InjectMocks
    private AddPaymentScheduleServiceImp service;

    @Test
    public void testAddPaymentSuccess() {
        var request = DTOPaymentCreator.getAddPaymentRequest();
        var account = EntityCreator.getAccount();
        var payment = EntityCreator.getPayment();

        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.convertPaymentDTORequestToPayment(request, account))
                .thenReturn(EntityCreator.getPayment());

        when(util.convertEntityToAddResponse(payment))
                .thenReturn(DTOPaymentCreator.getAddPaymentScheduleDTOResponse());

        assertEquals(DTOPaymentCreator.getAddPaymentScheduleDTOResponse(), service.execute(request));
    }

    @Test
    public void testAddPaymentNotFoundException() {
        var request = DTOPaymentCreator.getAddPaymentRequest();

        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));
    }

}