package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerCreationService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    public ManagerResponseDTO createManager(AddManagerRequest request) {
        Manager manager = util.convertAddRequestToManager(request);
        Manager savedManager = saveManager(manager);
        return util.convertManagerToResponse(savedManager);
    }

    public Manager saveManager(Manager manager) {
        return repository.save(manager);
    }
}
