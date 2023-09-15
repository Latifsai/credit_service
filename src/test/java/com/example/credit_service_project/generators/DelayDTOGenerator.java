package com.example.credit_service_project.generators;

import com.example.credit_service_project.dto.delayDTO.DelayResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DelayDTOGenerator {
    public static DelayResponse getResponse() {
        return DelayResponse.builder()
                .id(UUID.fromString("cff560e4-45af-4e3f-9d2c-02c8f8bbfd4d"))
                .timeOfDelay(LocalDateTime.now())
                .sumOfDelay(BigDecimal.TEN)
                .build();
    }
}
