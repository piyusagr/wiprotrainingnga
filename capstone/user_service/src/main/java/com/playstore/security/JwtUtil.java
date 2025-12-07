package com.playstore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private Long expiration;
    
//    Generate signing key
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 bytes");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
//    Generate JWT token
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
    
//    Extract username from token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    
    //    Extract role from token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
    
    //    Validate token
    public boolean isTokenValid(String token, String username) {
        try {
            Claims claims = extractClaims(token);
            return username.equals(claims.getSubject()) && 
                   !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    
//    Extract all claims from token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())      
                .build()
                .parseClaimsJws(token)               
                .getBody();                          
    }
}
