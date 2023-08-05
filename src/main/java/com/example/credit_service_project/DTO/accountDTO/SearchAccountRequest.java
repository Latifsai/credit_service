package com.example.credit_service_project.DTO.accountDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccountRequest {
    @NotNull(message = "Id must not be null!")
    private UUID id;

    @NotNull(message = "accountNumber must not be null!")
    @NotEmpty(message = "accountNumber must not be empty!")
    private String accountNumber;
}
