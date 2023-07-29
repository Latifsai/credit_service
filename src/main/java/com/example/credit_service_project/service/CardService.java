package com.example.credit_service_project.service;

public interface CardService<R,I> {
    R execute(I i);
}
