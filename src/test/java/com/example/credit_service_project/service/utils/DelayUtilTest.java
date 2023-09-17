package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.entity.Delay;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DelayUtilTest {
    @InjectMocks
    private DelayUtil util;
    private final Delay delay = EntityCreator.getDelay();

    @Test
    @DisplayName("Test createEntity method")
    void createEntity() {
        assertNotNull(util.createEntity(BigDecimal.TEN));
    }

    @Test
    @DisplayName("Test convertToResponse method")
    void convertToResponse() {
        assertNotNull(util.convertToResponse(delay));
    }
}