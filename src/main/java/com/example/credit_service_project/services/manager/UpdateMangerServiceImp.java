package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.services.ManagerService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import com.example.credit_service_project.services.utils.ManagerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateMangerServiceImp implements ManagerService<ManagerResponseDTO, UpdateManagerRequest> {

    private final AddManagerServiceImp addManagerService;
    private final SearchManagerServiceImp searchManagerService;
    private final ManagerUtil util;

    @Override
    public ManagerResponseDTO execute(UpdateManagerRequest request) {
        Optional<Manager> manager = searchManagerService.findManagerById(request.getId());
        return manager.map(m -> util.update(m, request))
                .map(updatedManager -> {
                    addManagerService.saveManager(updatedManager);
                    return util.convertManagerToResponse(updatedManager);
                })
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE));
    }
}
