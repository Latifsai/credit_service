package com.example.credit_service_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CreditServiceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditServiceProjectApplication.class, args);
    }

}
