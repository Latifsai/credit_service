package com.example.credit_service_project.DTO.cardDTO;

import lombok.Value;
import java.time.LocalDate;

@Value
public class AddCardDTORequest {
    private String holderName;
    private LocalDate openingDate;
    private String deliveryAddress;

}
