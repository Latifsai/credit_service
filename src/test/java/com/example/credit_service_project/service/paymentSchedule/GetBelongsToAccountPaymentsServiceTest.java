package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.dto.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.dto.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.PaymentSchedule;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetBelongsToAccountPaymentsServiceTest {

    @Mock
    private PaymentScheduleRepository repository;
    @Mock
    private PaymentScheduleUtil util;
    @Mock
    private AccountSearchService accountSearchService;
    @InjectMocks
    private GetBelongsToAccountPaymentsService service;

    @Test
    @DisplayName("Test getBelongsPayments methods")
    public void getBelongsPayments() {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);
        Account account = EntityCreator.getAccount();
        PaymentSchedule payment = EntityCreator.getPayment();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(repository.findAllByAccount(account)).thenReturn(List.of(payment));
        when(util.convertEntityToPaymentResponse(payment)).thenReturn(PaymentDTOGenerator.getPaymentResponseDTO());

        assertEquals(new GetBelongsPaymentsResponse(account.getId(), account.getAccountNumber(),
                List.of(PaymentDTOGenerator.getPaymentResponseDTO())), service.getBelongsToAccountPayments(request));
    }

    @Test
    @DisplayName("Test getBelongsPayments methods throws NotFoundException")
    public void testGetBelongsPaymentsNotFoundException() {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);
        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> service.getBelongsToAccountPayments(request));
    }
}