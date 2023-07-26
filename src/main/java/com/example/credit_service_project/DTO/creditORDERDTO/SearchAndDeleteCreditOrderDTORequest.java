package com.example.credit_service_project.DTO.creditORDERDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class SearchAndDeleteCreditOrderDTORequest {
    UUID id;
    String number;
}
