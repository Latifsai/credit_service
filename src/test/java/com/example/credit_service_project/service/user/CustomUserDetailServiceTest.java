package com.example.credit_service_project.service.user;

import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repository.UserRepository;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private CustomUserDetailService customUserDetailService;

    @Test
    @DisplayName("Test loadUserByUsername method")
    public void loadUserByUsername() {
        User user = EntityCreator.getUser();
        when(repository.findByName(anyString())).thenReturn(Optional.of(user));
        assertEquals(user, customUserDetailService.loadUserByUsername(anyString()));
    }

    @Test
    @DisplayName("Test loadUserByUsername method throws UsernameNotFoundException")
    public void loadUserByUsernameUsernameNotFoundException() {
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailService.loadUserByUsername(anyString()));
    }

}