package com.ethbackend.ethbackend.Utils;


import com.ethbackend.ethbackend.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {


    public String generateJWT(Authentication authentication) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Constants.expireTime);
        String username = authentication.getName();


        String token = Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,Constants.Secret).compact();


        return token;

    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(Constants.Secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateJWT(String token) {
        try {
            Jwts.parser().setSigningKey(Constants.Secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
