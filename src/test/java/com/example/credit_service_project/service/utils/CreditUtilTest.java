package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreditUtilTest {

    @InjectMocks
    private CreditUtil util;

    private Credit credit;

    @BeforeEach
    public void init() {
        credit = EntityCreator.getCredit();
    }

    @Test
    @DisplayName("Test createCreditFromData method")
    void createCreditFromData() {
        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.randomUUID(), null, UUID.randomUUID(),
                UUID.randomUUID(), 12, 0, "consumer credit");
        Account account = EntityCreator.getAccount();
        Agreement agreement = EntityCreator.getAgreement();
        CreditOrder creditOrder = EntityCreator.getCreditOrder();

        assertEquals(credit, util.createCreditFromData(request, account, agreement, creditOrder));
    }

    @Test
    @DisplayName("Test convertResponse method")
    void convertResponse() {
        List<PaymentResponseDTO> list = List.of(PaymentDTOGenerator.getPaymentResponseDTO());
        CreateCreditDTOResponse result = util.convertResponse(credit, list);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Test convertToCreditResponse method")
    void convertToCreditResponse() {
        assertNotNull(util.convertToCreditResponse(credit));
    }
}