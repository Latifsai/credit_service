package com.example.credit_service_project.services.role;

import com.example.credit_service_project.entity.Role;
import com.example.credit_service_project.repositories.RoleRepository;
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
     * This method will be used to find the ROLE
     * @param roleName that means MANAGER or CLIENT
     * @return ROLE
     */
    public Role findByRoleName(String roleName) {
        return repository.findByName(roleName.toUpperCase())
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ROLE_MESSAGE));
    }

}
