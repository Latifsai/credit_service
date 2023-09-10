package com.example.credit_service_project.services.auth;

import com.example.credit_service_project.validation.exceptions.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    void testGetUsername() {
        String username = "Olga";

        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        String resultUsername = provider.getUsername(token);

        assertEquals(username, resultUsername);
    }

    @Test
    void validateToken() {
        String username = "Olga";

        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        assertTrue(provider.validateToken(token));
    }

    @Test
    void validateTokenException() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJuYW1lIjoiT2xnYSIsImlhdCI6IiJ9.2RDCdv7SK4sOGCr04oN2efITl6sPv0h2SDU0rv0LQgU";
        assertThrows(InvalidJwtException.class, () -> provider.validateToken(token));
    }


}