package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repositories.ManagerRepository;
import com.example.credit_service_project.services.utils.ManagerUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerSearchService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Transactional(readOnly = true)
    public ManagerResponseDTO searchManager(UUID id) {
        Manager manager = findManagerById(id);
        log.info("Search manager with ID: {}", id);
        return util.convertManagerToResponse(manager);
    }

    public Manager findManagerById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
