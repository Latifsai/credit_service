package com.example.credit_service_project.DTO.creditORDERDTO;

import com.example.credit_service_project.entity.CreditOrder;
import lombok.Value;

import java.util.List;

@Value
public class GetAllCreditOrdersListResponse {
    List<CreditOrder> credits;
}
