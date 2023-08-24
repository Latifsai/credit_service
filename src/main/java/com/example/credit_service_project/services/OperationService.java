package com.example.credit_service_project.services;

public interface OperationService<R, I> {
    R execute(I i);
}
