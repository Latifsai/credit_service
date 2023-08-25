package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.services.ManagerService;
import com.example.credit_service_project.services.utils.ManagerUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchManagerServiceImp implements ManagerService<ManagerResponseDTO, UUID> {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Transactional(readOnly = true)
    @Override
    public ManagerResponseDTO execute(UUID id) {
        Manager manager = findManagerById(id);
        return util.convertManagerToResponse(manager);
    }

    public Manager findManagerById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
