package com.example.credit_service_project.services;

public interface ManagerService<R,I> {
    R execute(I i);
}
