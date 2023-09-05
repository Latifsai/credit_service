package com.example.credit_service_project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/secured")).authenticated()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/info")).authenticated()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/admin")).hasRole("ADMIN")
                .anyRequest().permitAll();
        return null;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("manager")
                        .password(passwordEncoder().encode("managerPassword"))
                        .roles("MANAGER")
                        .build(),
                User.withUsername("client")
                        .password(passwordEncoder().encode("clientPassword"))
                        .roles("CLIENT")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
