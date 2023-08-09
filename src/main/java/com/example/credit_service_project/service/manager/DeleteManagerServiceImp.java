package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.ManagerService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.ManagerNotFoundException;
import com.example.credit_service_project.service.utils.ManagerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteManagerServiceImp implements ManagerService<ManagerResponseDTO, UUID> {

    private final ManagerUtil util;
    private final ManagerRepository repository;

    @Override
    public ManagerResponseDTO execute(UUID id) {
        Optional<Manager> managerOptional = repository.findById(id);
        return managerOptional.map(manager -> {
            var response = util.convertManagerToResponse(manager);
            repository.delete(manager);
            return response;
        }).orElseThrow(() -> new ManagerNotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
