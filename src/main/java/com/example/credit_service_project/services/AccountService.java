package com.example.credit_service_project.services;



public interface AccountService<R, I> {
    R execute(I i);
}
