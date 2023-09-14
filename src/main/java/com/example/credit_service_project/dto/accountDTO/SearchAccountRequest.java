package com.example.credit_service_project.dto.accountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccountRequest {
    private UUID id;
    private String accountNumber;
}
