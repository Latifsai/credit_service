package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.ManagerService;
import com.example.credit_service_project.service.utils.ManagerUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchManagerServiceImp implements ManagerService<ManagerResponseDTO, UUID> {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Transactional(readOnly = true)
    @Override
    public ManagerResponseDTO execute(UUID id) {
        Optional<Manager> manager = findManagerById(id);
        return manager.map(util::convertManagerToResponse)
                .orElseThrow(() -> new ManagerNotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }

    public Optional<Manager> findManagerById(UUID id) {
        return repository.findById(id);
    }
}
