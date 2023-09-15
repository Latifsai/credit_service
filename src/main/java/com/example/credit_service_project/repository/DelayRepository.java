package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.entity.Delay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DelayRepository extends JpaRepository<Delay, UUID> {
    public List<Delay> findAllByCreditHistory(CreditHistory history);

}
