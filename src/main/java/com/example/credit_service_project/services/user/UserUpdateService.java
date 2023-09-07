package com.example.credit_service_project.services.user;

import com.example.credit_service_project.DTO.user.UserResponseDTO;
import com.example.credit_service_project.DTO.user.UpdateClientRequest;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.services.role.RoleService;
import com.example.credit_service_project.services.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUpdateService {

    private final UserSearchService searchService;
    private final UserCreateService createService;
    private final RoleService roleService;
    private final UserUtil util;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO updateClient(UpdateClientRequest request) {
        User user = searchService.findUserById(request.getId());
        User updatedUser = util.updateClient(user, request);
        if (request.getRoleName() != null) {
            updatedUser.setRole(roleService.findByRoleName(request.getRoleName()));
        }

        if (request.getPassword() != null) {
            updatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        createService.saveClient(updatedUser);
        log.info("Update client with id: {}", user.getId());
        return util.convertUserToResponse(updatedUser);
    }

}
