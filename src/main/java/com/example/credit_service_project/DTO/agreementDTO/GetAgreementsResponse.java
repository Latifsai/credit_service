package com.example.credit_service_project.DTO.agreementDTO;

import com.example.credit_service_project.entity.Agreement;
import lombok.Value;

import java.util.List;

@Value
public class GetAgreementsResponse {
    List<Agreement> agreements;
}
