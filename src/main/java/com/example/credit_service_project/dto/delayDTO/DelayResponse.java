package com.example.credit_service_project.dto.delayDTO;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class DelayResponse {
    UUID id;
    LocalDateTime timeOfDelay;
    BigDecimal sumOfDelay;
}
