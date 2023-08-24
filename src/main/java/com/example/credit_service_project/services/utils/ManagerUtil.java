package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.entity.Manager;
import org.springframework.stereotype.Service;

@Service
public class ManagerUtil {
    public Manager convertAddRequestToManager(AddManagerRequest request) {
        Manager manager = new Manager();
        manager.setName(request.getName());
        manager.setSurname(request.getSurname());
        manager.setEmail(request.getEmail());
        return manager;
    }

    public ManagerResponseDTO convertManagerToResponse(Manager manager) {
        return new ManagerResponseDTO(
                manager.getId(),
                manager.getName(),
                manager.getSurname(),
                manager.getEmail());
    }

    public Manager update(Manager manager, UpdateManagerRequest request) {
        if (check(request.getEmail())) manager.setEmail(request.getEmail());
        if (check(request.getSurname())) manager.setSurname(request.getSurname());
        return manager;
    }

    private boolean check(String criteria) {
        return criteria != null && !criteria.trim().isEmpty();
    }
}
