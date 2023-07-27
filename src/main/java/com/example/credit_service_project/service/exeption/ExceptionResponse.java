package com.example.credit_service_project.service.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private List<ErrorException> errors;
}
