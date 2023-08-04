package com.example.credit_service_project.service;

public interface ClientService<R,I> {
    R execute(I i);
}
