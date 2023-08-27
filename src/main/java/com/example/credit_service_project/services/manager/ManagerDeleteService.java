package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repositories.ManagerRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerDeleteService {

    private final ManagerUtil util;
    private final ManagerRepository repository;

    public ManagerResponseDTO execute(UUID id) {
        Optional<Manager> managerOptional = repository.findById(id);
        log.info("Delete manager with ID: {}", id);
        return managerOptional.map(manager -> {
            ManagerResponseDTO response = util.convertManagerToResponse(manager);
            repository.delete(manager);
            return response;
        }).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
