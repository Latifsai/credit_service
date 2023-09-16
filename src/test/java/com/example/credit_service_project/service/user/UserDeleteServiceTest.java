package com.example.credit_service_project.service.user;

import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repository.UserRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDeleteServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserSearchService searchService;
    @InjectMocks
    private UserDeleteService deleteService;

    @Test
    @DisplayName("Test deleteUser method")
    public void deleteUser() {
        User user = EntityCreator.getUser();
        when(searchService.findUserById(EntityCreator.getUser().getId())).thenReturn(user);

        deleteService.delete(user.getId());

        verify(repository, times(1)).delete(user);
        verify(searchService, times(1)).findUserById(user.getId());
    }

    @Test
    @DisplayName("Test deleteUser method throws NotFoundException")
    public void deleteUserNotFoundException() {
        UUID id = UUID.randomUUID();

        when(searchService.findUserById(id)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> deleteService.delete(id));
    }
}