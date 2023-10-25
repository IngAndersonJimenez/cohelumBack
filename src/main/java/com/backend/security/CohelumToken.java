package com.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CohelumToken {

    private final static String ACCESS_TOKEN = "Toekncohelum1598743ghsvdhasfdthaschzvhxcdwrdfjhxcvghadasfjxash";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 240L;

    public static String createToken(String nameUser, String emailUser) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000L;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", nameUser);

        return Jwts.builder()
                .setSubject(emailUser)
                .setExpiration(expirationDate)
                .addClaims(stringObjectMap)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        } catch (JwtException jwtException) {
            return null;
        }
    }


}
