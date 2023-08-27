package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repositories.ManagerRepository;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerCreationService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    public ManagerResponseDTO createManager(AddManagerRequest request) {
        Manager manager = util.convertAddRequestToManager(request);
        Manager savedManager = saveManager(manager);
        log.info("Create and save manager: {}", manager);
        return util.convertManagerToResponse(savedManager);
    }

    public Manager saveManager(Manager manager) {
        return repository.save(manager);
    }
}
