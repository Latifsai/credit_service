package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.AgreementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AgreementUtilTest {

    @InjectMocks
    private AgreementUtil util;

    @Test
    @DisplayName("Test convertCreateRequestToEntity method")
    void convertCreateRequestToEntity() {
        CreateAgreementRequest request = new CreateAgreementRequest(UUID.randomUUID(), 10);
        CreditOrder order = EntityCreator.getCreditOrder();

        Agreement result = util.convertCreateRequestToEntity(request, order);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Test convertCreateRequestToEntityAgreement method throw an Exception")
    void convertCreateRequestToEntityAgreementException() {
        CreateAgreementRequest request = new CreateAgreementRequest(UUID.randomUUID(), 10);
        CreditOrder order = EntityCreator.getCreditOrder();
        order.setCreditOrderStatus(CreditOrderStatus.DECLINED);

        assertThrows(AgreementException.class, () -> util.convertCreateRequestToEntity(request, order));
    }

    @Test
    @DisplayName("Test convertToResponse method")
    void convertToResponse() {
        Agreement agreement = EntityCreator.getAgreement();

        assertNotNull(util.convertToResponse(agreement));
    }
}