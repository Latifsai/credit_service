package com.example.credit_service_project.services.user;

import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    protected final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorsMessage.NOT_FOUND_USER_MESSAGE + " with name: " + username));
    }
}
