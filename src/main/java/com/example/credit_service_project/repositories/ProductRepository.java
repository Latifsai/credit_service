package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {

    Optional<Product> findById(Long id);
}
