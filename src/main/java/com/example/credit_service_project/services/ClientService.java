package com.example.credit_service_project.services;

public interface ClientService<R,I> {
    R execute(I i);
}
