package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repositories.AgreementRepository;
import com.example.credit_service_project.services.generators.AgreementDTOGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.AgreementUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void getAllAgreements() {
        List<Agreement> agreements = List.of(EntityCreator.getAgreement());

        when(repository.findAll()).thenReturn(agreements);
        when(util.convertToResponse(agreements.get(0))).thenReturn(AgreementDTOGenerator.getResponse());

        List<AgreementResponse> result = getAllAgreementsService.getAllAgreements();
        verify(repository, times(1)).findAll();
        assertEquals(1, result.size());
    }
}