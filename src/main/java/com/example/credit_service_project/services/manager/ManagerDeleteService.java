package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerDeleteService {

    private final ManagerUtil util;
    private final ManagerRepository repository;

    public ManagerResponseDTO execute(UUID id) {
        Optional<Manager> managerOptional = repository.findById(id);
        return managerOptional.map(manager -> {
            ManagerResponseDTO response = util.convertManagerToResponse(manager);
            repository.delete(manager);
            return response;
        }).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
