package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.generators.AgreementDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.AgreementUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgreementCreateServiceTest {

    @Mock
    private AgreementRepository repository;
    @Mock
    private CreditOrderSearchService searchCreditOrderService;
    @Mock
    private AgreementUtil util;
    @InjectMocks
    private AgreementCreateService agreementCreateService;

    @Test
    @DisplayName(value = "Test create agreement")
    void createAgreement() {
        CreateAgreementRequest request = new CreateAgreementRequest(UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"),
                10);
        CreditOrder creditOrder = EntityCreator.getCreditOrder();
        Agreement agreement = EntityCreator.getAgreement();
        AgreementResponse response = AgreementDTOGenerator.getResponse();

        when(searchCreditOrderService.findById(request.getCreditOrderID())).thenReturn(creditOrder);
        when(util.convertCreateRequestToEntity(request, creditOrder)).thenReturn(agreement);
        when(repository.save(agreement)).thenReturn(agreement);
        when(util.convertToResponse(agreement)).thenReturn(response);

        AgreementResponse result = agreementCreateService.createAgreement(request);


        verify(searchCreditOrderService, times(1)).findById(request.getCreditOrderID());
        verify(util, times(1)).convertCreateRequestToEntity(request, creditOrder);
        verify(repository, times(1)).save(agreement);
        verify(util, times(1)).convertToResponse(agreement);
        assertEquals(response, result);

    }

    @Test
    @DisplayName(value = "Test save agreement")
    void saveAgreement() {
        Agreement agreement = EntityCreator.getAgreement();

        when(repository.save(agreement)).thenReturn(agreement);

        Agreement result = agreementCreateService.saveAgreement(agreement);

        verify(repository, times(1)).save(agreement);
        assertEquals(agreement, result);
    }
}