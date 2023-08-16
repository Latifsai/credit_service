package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Product;
import org.hibernate.boot.model.internal.PropertyPreloadedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {


}
