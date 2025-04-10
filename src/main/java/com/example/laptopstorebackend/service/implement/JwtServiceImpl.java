package com.example.laptopstorebackend.service.implement;

import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public class JwtServiceImpl {


    private final String jwtSecret = "IamLoser1908200220028091resoLmaI1908200220028091";

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
