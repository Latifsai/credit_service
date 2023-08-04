package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.utils.ManagerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllManagersService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    public List<ManagerResponseDTO> execute() {
        return repository.findAll().stream()
                .map(manager -> util.convertManagerToResponse(manager))
                .toList();
    }
}
