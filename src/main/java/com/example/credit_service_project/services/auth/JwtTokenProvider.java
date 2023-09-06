package com.example.credit_service_project.services.auth;

import com.example.credit_service_project.validation.exceptions.InvalidJwtException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("roles", roleList);
        Date issudeDate = new Date();
        Date expiredDate = new Date(issudeDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
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

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

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


