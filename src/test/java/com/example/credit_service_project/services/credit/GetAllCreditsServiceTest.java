package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.DTO.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.repositories.CreditRepository;
import com.example.credit_service_project.services.generators.CreditDTOGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CreditUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllCreditsServiceTest {
    @Mock
    private CreditRepository repository;
    @Mock
    private CreditUtil util;
    @InjectMocks
    private GetAllCreditsService getAllCreditsService;

    @Test
    void getAllCredits() {
        List<Credit> credits = List.of(EntityCreator.getCredit());

        when(repository.findAll()).thenReturn(credits);
        when(util.convertToCreditResponse(credits.get(0))).thenReturn(CreditDTOGenerator.getResponse());

        List<CreditResponseDTO> result = getAllCreditsService.getAllCredits();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }
}