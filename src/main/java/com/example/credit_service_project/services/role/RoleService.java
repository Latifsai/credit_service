package com.example.credit_service_project.services.role;

import com.example.credit_service_project.entity.Role;
import com.example.credit_service_project.repositories.RoleRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository repository;

    // find by name

    /**
     * This method will be used to find the ROLE
     * @param roleName that means MANAGER or CLIENT
     * @return ROLE
     */
    public Role findByRoleName(String roleName) {
        return repository.findByName("ROLE_" + roleName)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ROLE_MESSAGE));
    }
//     get default
//    public Role getClientRole() {
//        return findByRoleName("CLIENT");
//    }
//     get manger role
//    public Role getManagerRole() {
//        return findByRoleName("MANAGER");
//    }
//
//    get all roles
//    public List<Role> getAllRoles() {
//        return repository.findAll();
//    }

}
