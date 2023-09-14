package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repositories.AgreementRepository;
import com.example.credit_service_project.services.generators.AgreementDTOGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.AgreementUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchAgreementServiceTest {

    @Mock
    private AgreementRepository repository;
    @Mock
    private AgreementUtil util;
    @InjectMocks
    private SearchAgreementService searchAgreementService;

    @Test
    void searchAgreement() {
        UUID id = UUID.randomUUID();
        Agreement agreement = EntityCreator.getAgreement();

        when(repository.findById(id)).thenReturn(Optional.of(agreement));
        when(util.convertToResponse(agreement)).thenReturn(AgreementDTOGenerator.getResponse());

        AgreementResponse result = searchAgreementService.searchAgreement(id);
        assertNotNull(result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.of(EntityCreator.getAgreement()));

        Agreement result = searchAgreementService.findById(id);
        verify(repository, times(1)).findById(id);
        assertNotNull(result);
    }

    @Test
    void findByIdNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> searchAgreementService.findById(id));
        verify(repository, times(1)).findById(id);
    }
}