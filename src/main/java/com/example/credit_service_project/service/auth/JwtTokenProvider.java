package com.example.credit_service_project.service.auth;

import com.example.credit_service_project.validation.exceptions.InvalidJwtException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class JwtTokenProvider {
    private final String secret;
    private final Duration jwtLifetime;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}")String secret, @Value("${jwt.lifetime}") Duration jwtLifetime) {
        this.secret = secret;
        this.jwtLifetime = jwtLifetime;
    }

    /**
     * Here will be created a token upon username
     * @param username String
     * @return String
     */
    public String generateToken(String username) {
        Date issudeDate = new Date();
        Date expiredDate = new Date(issudeDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issudeDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * The method will validate the token. If token is not valid then throws the InvalidJwtException
     * @param authToken String
     * @return boolean
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new InvalidJwtException(e.getMessage());
            // Invalid JWT signature
        } catch (MalformedJwtException e) {
            throw new InvalidJwtException(e.getMessage());
            // Invalid JWT token
        } catch (ExpiredJwtException e) {
            throw new InvalidJwtException(e.getMessage());
            // Expired JWT token
        } catch (UnsupportedJwtException e) {
            throw new InvalidJwtException(e.getMessage());
            // Unsupported JWT token
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty
            throw new InvalidJwtException(e.getMessage());
        }

    }
}


