package com.example.credit_service_project.services;

public interface ProductService<R, I> {
    R execute(I i);
}
