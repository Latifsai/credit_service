package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.ManagerService;
import com.example.credit_service_project.service.utils.ManagerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AddManagerServiceImp implements ManagerService<ManagerResponseDTO, AddManagerRequest> {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Override
    public ManagerResponseDTO execute(AddManagerRequest request) {
        Manager manager = util.convertAddRequestToManager(request);
        Manager savedManager = saveManager(manager);
        return util.convertManagerToResponse(savedManager);
    }

    public Manager saveManager(Manager manager) {
        return repository.save(manager);
    }
}
