package com.example.credit_service_project.services;

public interface CreditOrderService<R,I> {
    R execute(I i);
}
