package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.generators.AgreementDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.AgreementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementUtilTest {

    @Mock
    private AgreementUtil util;

    @Test
    void convertCreateRequestToEntity() {
        CreateAgreementRequest request = new CreateAgreementRequest(UUID.randomUUID(), 10);
        Agreement agreement = EntityCreator.getAgreement();
        CreditOrder order = EntityCreator.getCreditOrder();

        when(util.convertCreateRequestToEntity(request, order)).thenReturn(agreement);
        Agreement result = util.convertCreateRequestToEntity(request, order);

        assertEquals(agreement, result);
    }

    @Test
    void convertCreateRequestToEntityAgreementException() {
        CreateAgreementRequest request = new CreateAgreementRequest(UUID.randomUUID(), 10);
        CreditOrder order = EntityCreator.getCreditOrder();
        order.setCreditOrderStatus(CreditOrderStatus.DECLINED);

        when(util.convertCreateRequestToEntity(request, order)).thenThrow(AgreementException.class);

        assertThrows(AgreementException.class, () -> util.convertCreateRequestToEntity(request, order));
    }

    @Test
    void convertToResponse() {
        AgreementResponse response = AgreementDTOGenerator.getResponse();
        Agreement agreement = EntityCreator.getAgreement();

        when(util.convertToResponse(agreement)).thenReturn(response);

        assertEquals(response, util.convertToResponse(agreement));
    }
}