package com.example.credit_service_project.service.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.ManagerService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.ManagerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateMangerServiceImp implements ManagerService<ManagerResponseDTO, UpdateManagerRequest> {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Override
    public ManagerResponseDTO execute(UpdateManagerRequest request) {
        Optional<Manager> manager = repository.findById(request.getId());
        return manager.map(m -> util.update(m, request))
                .map(updatedManager -> {
                    repository.save(updatedManager);
                    return util.convertManagerToResponse(updatedManager);
                })
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
