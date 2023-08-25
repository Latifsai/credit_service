package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.services.ManagerService;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMangerServiceImp implements ManagerService<ManagerResponseDTO, UpdateManagerRequest> {

    private final AddManagerServiceImp addManagerService;
    private final SearchManagerServiceImp searchManagerService;
    private final ManagerUtil util;

    @Override
    public ManagerResponseDTO execute(UpdateManagerRequest request) {
        Manager manager = searchManagerService.findManagerById(request.getId());
        Manager updatedManager = util.update(manager, request);
        addManagerService.saveManager(updatedManager);
        return util.convertManagerToResponse(updatedManager);
    }
}
