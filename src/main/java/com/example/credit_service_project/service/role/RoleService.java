package com.example.credit_service_project.service.role;

import com.example.credit_service_project.entity.Role;
import com.example.credit_service_project.repository.RoleRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository repository;

    /**
     * This method will be used to find the Role. Parameters or MANAGER either CLIENT
     * @param roleName String
     * @return Role
     */
    public Role findByRoleName(String roleName) {
        return repository.findByName(roleName.toUpperCase())
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ROLE_MESSAGE));
    }

}
