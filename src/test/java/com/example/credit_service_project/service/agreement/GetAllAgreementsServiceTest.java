package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.generators.AgreementDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.AgreementUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllAgreementsServiceTest {

    @Mock
    private AgreementUtil util;
    @Mock
    private AgreementRepository repository;
    @InjectMocks
    private GetAllAgreementsService getAllAgreementsService;

    @Test
    @DisplayName(value = "Test get all agreements")
    void getAllAgreements() {
        List<Agreement> agreements = Collections.singletonList(EntityCreator.getAgreement());

        when(repository.findAll()).thenReturn(agreements);
        when(util.convertToResponse(agreements.get(0))).thenReturn(AgreementDTOGenerator.getResponse());

        List<AgreementResponse> result = getAllAgreementsService.getAllAgreements();
        verify(repository, times(1)).findAll();
        verify(util, times(1)).convertToResponse(agreements.get(0));
        assertEquals(1, result.size());
    }
}