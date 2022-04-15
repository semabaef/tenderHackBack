package ru.TagAll.tenderHackBack.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("@{jwt.secret}")
    private String jwtSecret;

    public String generateToken(String email) {
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusHours(1L)
                .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String generateAccessToken(String email) {
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusHours(2L)
                .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getWordForToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
