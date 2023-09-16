package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.dto.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.generators.CreditDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CreditUtil;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Test get all credits method")
    void getAllCredits() {
        List<Credit> credits = List.of(EntityCreator.getCredit());

        when(repository.findAll()).thenReturn(credits);
        when(util.convertToCreditResponse(credits.get(0))).thenReturn(CreditDTOGenerator.getResponse());

        List<CreditResponseDTO> result = getAllCreditsService.getAllCredits();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
        verify(util, times(1)).convertToCreditResponse(credits.get(0));
    }
}