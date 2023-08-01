package com.example.credit_service_project.service;

public interface OperationService<R, I> {
    R execute(I i);
}
