package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CheckCreditOrderStatusResponse;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckCreditOrderStatusServiceTest {

    @Mock
    private CreditOrderSearchService searchService;
    @InjectMocks
    private CheckCreditOrderStatusService checkCreditOrderStatusService;

    @Test
    void checkOrderStatus() {
        UUID id = UUID.randomUUID();
        CreditOrder order = EntityCreator.getCreditOrder();

        when(searchService.findById(id)).thenReturn(order);

        CheckCreditOrderStatusResponse result = checkCreditOrderStatusService.checkOrderStatus(id);

        assertNotNull(result);
        verify(searchService, times(1)).findById(id);
    }
}