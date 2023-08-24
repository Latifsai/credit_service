package com.example.credit_service_project.services;

public interface CardService<R,I> {
    R execute(I i);
}
