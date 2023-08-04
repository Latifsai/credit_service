package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
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
        var manager = util.convertAddRequestToManager(request);
        repository.save(manager);
        return util.convertManagerToResponse(manager);
    }
}
