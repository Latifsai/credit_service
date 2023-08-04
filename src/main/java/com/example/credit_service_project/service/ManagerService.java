package com.example.credit_service_project.service;

public interface ManagerService<R,I> {
    R execute(I i);
}
