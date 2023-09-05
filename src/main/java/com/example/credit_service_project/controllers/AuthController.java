package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.auth.JwtResponse;
import com.example.credit_service_project.DTO.auth.JwtRequest;
import com.example.credit_service_project.DTO.auth.RegistrationRequest;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.services.user.UserCreateService;
import com.example.credit_service_project.services.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserCreateService userCreateService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("auth")
    public ResponseEntity<?> createAuthToken(JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userCreateService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/resgestration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationRequest request) {
        if (request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
