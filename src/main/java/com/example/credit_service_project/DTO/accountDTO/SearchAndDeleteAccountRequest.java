package com.example.credit_service_project.DTO.accountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAndDeleteAccountRequest {
    UUID id;
    String accountNumber;
}
