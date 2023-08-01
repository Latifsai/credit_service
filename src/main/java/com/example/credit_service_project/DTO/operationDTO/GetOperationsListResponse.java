package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.Operation;
import lombok.Value;

import java.util.List;

@Value
public class GetOperationsListResponse {
    List<OperationResponseDTO> list;
}
