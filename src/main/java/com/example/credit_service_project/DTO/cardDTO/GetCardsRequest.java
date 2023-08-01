package com.example.credit_service_project.DTO.cardDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCardsRequest {
    private String accountNumber;
}
