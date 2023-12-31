package com.example.credit_service_project.service.user;

import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.service.role.RoleService;
import com.example.credit_service_project.service.utils.UserUtil;
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

    /**
     * Update Client
     * @param request UpdateClientRequest
     * @return UserResponseDTO
     */
    public UserResponseDTO updateClient(UpdateClientRequest request) {
        User user = searchService.findUserById(request.getId());
        User updatedUser = util.updateClient(user, request);
        if (request.getRoleName() != null) {
            updatedUser.setRole(roleService.findByRoleName(request.getRoleName()));
        }

        if (request.getPassword() != null) {
            updatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        createService.saveUser(updatedUser);
        log.info("Update client with id: {}", user.getId());
        return util.convertUserToResponse(updatedUser);
    }

}
