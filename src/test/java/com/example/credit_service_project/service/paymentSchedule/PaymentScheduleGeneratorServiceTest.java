package com.example.credit_service_project.service.paymentSchedule;

import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.repository.PaymentScheduleRepository;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentScheduleGeneratorServiceTest {

    @Mock
    private PaymentScheduleUtil util;
    @Mock
    private PaymentScheduleRepository repository;

    @InjectMocks
    private PaymentScheduleGeneratorService paymentScheduleGeneratorService;

    @Test
    void generatePaymentSchedule() {

        Account account = EntityCreator.getAccount();
        Credit credit = EntityCreator.getCredit();
        Product product = EntityCreator.getProduct();
        Agreement agreement = EntityCreator.getAgreement();

        BigDecimal[] mockPayments = new BigDecimal[12];
        when(util.calculatePayment(12, credit.getInterestRate(), credit.getCreditSum(), product)).thenReturn(mockPayments);

        PaymentSchedule mockPaymentSchedule = EntityCreator.getPayment();
        when(util.convertToPayment(account)).thenReturn(mockPaymentSchedule);

        PaymentResponseDTO mockResponseDTO = PaymentDTOGenerator.getPaymentResponseDTO();
        when(util.convertEntityToPaymentResponse(mockPaymentSchedule)).thenReturn(mockResponseDTO);

        List<PaymentResponseDTO> responses = paymentScheduleGeneratorService.generatePaymentSchedule(credit, product, account, agreement);

        verify(util, times(1)).calculatePayment(12, credit.getInterestRate(), credit.getCreditSum(), product);
        verify(util, times(12)).convertToPayment(account);
        verify(util, times(12)).convertEntityToPaymentResponse(mockPaymentSchedule);
        verify(repository, times(12)).save(any(PaymentSchedule.class));

        assertEquals(12, responses.size());
    }
}