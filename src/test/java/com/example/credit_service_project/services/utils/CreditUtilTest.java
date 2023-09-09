package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.DTO.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.services.generators.CreditDTOGenerator;
import com.example.credit_service_project.services.generators.DTOPaymentCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditUtilTest {

    @Mock
    private CreditUtil util;

    private Credit credit;

    @BeforeEach
    public void init() {
        credit = EntityCreator.getCredit();
    }

    @Test
    void createCreditFromData() {
        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.randomUUID(), null, UUID.randomUUID(),
                UUID.randomUUID(), 12, 0, "consumer credit");

        Account account = EntityCreator.getAccount();
        Agreement agreement = EntityCreator.getAgreement();
        CreditOrder creditOrder = EntityCreator.getCreditOrder();

        when(util.createCreditFromData(request, account, agreement, creditOrder)).thenReturn(credit);

        assertEquals(credit, util.createCreditFromData(request, account, agreement, creditOrder));
    }

    @Test
    void convertResponse() {
        CreateCreditDTOResponse response = CreditDTOGenerator.getCreationResponse();
        List<PaymentResponseDTO> list = List.of(DTOPaymentCreator.getPaymentResponseDTO());

        when(util.convertResponse(credit, list)).thenReturn(response);

        CreateCreditDTOResponse result = util.convertResponse(credit, list);
        assertEquals(response, result);
    }

    @Test
    void convertToCreditResponse() {
        CreditResponseDTO response = CreditDTOGenerator.getResponse();

        when(util.convertToCreditResponse(credit)).thenReturn(response);
        assertEquals(response, util.convertToCreditResponse(credit));
    }
}