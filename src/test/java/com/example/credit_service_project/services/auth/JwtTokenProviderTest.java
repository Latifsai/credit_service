package com.example.credit_service_project.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JwtTokenProviderTest {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    private JwtTokenProvider provider;

    @BeforeEach
    void setUp() {
        provider = new JwtTokenProvider(secret, jwtLifetime);
    }

    @Test
    void generateToken() {
        String username = "Olga";
        String token = provider.generateToken(username);

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();

        assertEquals(username, claims.getSubject());
        assertNotNull(issuedAt);
        assertTrue(issuedAt.before(new Date()));
        assertNotNull(expiration);
        assertEquals(username, claims.getSubject());
        assertNotNull(token);

    }

    @Test
    void getUsername() {
    }

    @Test
    void validateToken() {
    }
}