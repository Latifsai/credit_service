package com.example.credit_service_project.DTO.cardDTO;

import lombok.Value;
import java.time.LocalDate;

@Value
public class AddCardDTORequest {
     String holderName;
     LocalDate openingDate;
     String deliveryAddress;

}
