package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.entity.Credit;
import lombok.Value;

import java.util.List;

@Value
public class GetCreditsResponse {
    List<Credit> credits;
}
