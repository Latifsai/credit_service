package com.example.credit_service_project.generators;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.entity.enums.CreditHistoryStatus;
import org.springframework.security.core.parameters.P;

import java.util.UUID;

public class CreditHistoryDTOGenerator {
    public static CreditHistoryResponse getResponse() {
        return CreditHistoryResponse.builder()
                .id(UUID.fromString("6e6cdb58-2178-42da-86c4-10f8c2ee36c2"))
                .accountNumber("A6A3A20")
                .delayAmount(4)
                .status(CreditHistoryStatus.WELL)
                .build();
    }
}
