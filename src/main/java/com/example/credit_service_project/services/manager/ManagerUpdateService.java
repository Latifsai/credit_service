package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerUpdateService {

    private final ManagerCreationService addManagerService;
    private final ManagerSearchService searchManagerService;
    private final ManagerUtil util;

    public ManagerResponseDTO updateManager(UpdateManagerRequest request) {
        Manager manager = searchManagerService.findManagerById(request.getId());
        Manager updatedManager = util.update(manager, request);
        addManagerService.saveManager(updatedManager);
        log.info("Update manager with ID: {}", manager.getId());
        return util.convertManagerToResponse(updatedManager);
    }
}
