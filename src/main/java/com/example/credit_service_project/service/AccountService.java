package com.example.credit_service_project.service;

public interface AccountService<R, I> {
    R execute(I i);
}
