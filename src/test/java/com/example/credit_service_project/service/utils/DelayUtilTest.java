package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.entity.Delay;
import com.example.credit_service_project.generators.DelayDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DelayUtilTest {
    @Mock
    private DelayUtil util;
    private final Delay delay = EntityCreator.getDelay();

    @Test
    void createEntity() {
        when(util.createEntity(BigDecimal.TEN)).thenReturn(delay);

        assertEquals(delay, util.createEntity(BigDecimal.TEN));
    }

    @Test
    void convertToResponse() {
        var response = DelayDTOGenerator.getResponse();
        when(util.convertToResponse(delay)).thenReturn(response);

        assertEquals(response, util.convertToResponse(delay));
    }
}