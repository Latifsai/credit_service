package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllManagersService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Transactional(readOnly = true)
    public List<ManagerResponseDTO> execute() {
        return repository.findAll().stream()
                .map(util::convertManagerToResponse)
                .toList();
    }
}
