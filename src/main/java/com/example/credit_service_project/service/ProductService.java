package com.example.credit_service_project.service;

public interface ProductService<R, I> {
    R execute(I i);
}
