package com.example.credit_service_project.service;

public interface CreditOrderService<R,I> {
    R execute(I i);
}
