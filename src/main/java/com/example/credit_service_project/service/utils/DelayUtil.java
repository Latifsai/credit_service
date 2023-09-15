package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.delayDTO.DelayResponse;
import com.example.credit_service_project.entity.Delay;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DelayUtil {

    public Delay createEntity(BigDecimal fine) {
        Delay delay = new Delay();
        delay.setTimeOfDelay(LocalDateTime.now());
        delay.setSumOfDelay(fine);
        return delay;
    }

    public DelayResponse convertToResponse(Delay delay) {
        return DelayResponse.builder()
                .id(delay.getId())
                .sumOfDelay(delay.getSumOfDelay())
                .timeOfDelay(delay.getTimeOfDelay())
                .build();
    }

}
