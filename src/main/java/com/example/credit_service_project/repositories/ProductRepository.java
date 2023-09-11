package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {

    Optional<Product> findById(Long id);
}
