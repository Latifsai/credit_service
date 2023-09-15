package com.example.credit_service_project.service.user;

import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.generators.UserDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.role.RoleService;
import com.example.credit_service_project.service.utils.UserUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUpdateServiceTest {
    @Mock
    private UserUtil util;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserSearchService searchService;
    @Mock
    private UserCreateService createService;
    @Mock
    private RoleService roleService;
    @InjectMocks
    private UserUpdateService service;

    @Test
    public void testUpdateUserService() {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"),
                null, new BigDecimal("2000"), null, null, null, null, null);
        User user = EntityCreator.getUser();
        User updatedUser = EntityCreator.getUpdatedUser();

        when(searchService.findUserById(request.getId())).thenReturn(user);
        when(util.updateClient(user, request)).thenReturn(updatedUser);
        when(createService.saveClient(updatedUser)).thenReturn(updatedUser);
        when(util.convertUserToResponse(updatedUser)).thenReturn(UserDTOGenerator.getUpdateResponse());

        assertEquals(UserDTOGenerator.getUpdateResponse(), service.updateClient(request));
    }

    @Test
    public void testUpdateUserServiceChangeRole() {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"),
                null, new BigDecimal("2000"), null, null, null, "MANAGER", null);
        User user = EntityCreator.getUser();
        User updatedUser = EntityCreator.getUpdatedUser();

        when(searchService.findUserById(request.getId())).thenReturn(user);
        when(util.updateClient(user, request)).thenReturn(updatedUser);
        when(roleService.findByRoleName(request.getRoleName())).thenReturn(EntityCreator.getManagerRole());
        when(createService.saveClient(updatedUser)).thenReturn(updatedUser);
        when(util.convertUserToResponse(updatedUser)).thenReturn(UserDTOGenerator.getUpdateResponse());

        assertEquals(UserDTOGenerator.getUpdateResponse(), service.updateClient(request));
    }

    @Test
    public void testAddClientNotFoundException() {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"), null,
                new BigDecimal("2000"), null, null, null, null, null);
        when(searchService.findUserById(request.getId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.updateClient(request));
    }

}