package com.example.credit_service_project.service.role;

import com.example.credit_service_project.entity.Role;
import com.example.credit_service_project.repository.RoleRepository;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository repository;
    @InjectMocks
    RoleService roleService;

    @Test
    @DisplayName("Test findByRoleName method")
    void findByRoleName() {
        Role role = new Role(1, "MANAGER");
        String name = "MANAGER";

        when(repository.findByName(name.toUpperCase())).thenReturn(Optional.of(role));

        Role result = roleService.findByRoleName(name);

        assertEquals(role, result);
        verify(repository, times(1)).findByName(name.toUpperCase());
    }


    @Test
    @DisplayName("Test findByRoleName method throws NotFoundException")
    void findByRoleNameNotFoundException() {
        String name = "Admin";

        when(repository.findByName(name.toUpperCase())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.findByRoleName(name));
        verify(repository, times(1)).findByName(name.toUpperCase());
    }
}