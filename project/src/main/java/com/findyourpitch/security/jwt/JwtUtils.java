package com.findyourpitch.security.jwt;

import com.findyourpitch.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${findyourpitch.app.jwtSecret}")
    private String jwtSecret;

    @Value("${findyourpitch.app.jwtExpirationMs}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException exception) {
            throw new SignatureException("Invalid JWT signature " + exception.getMessage());
        } catch (MalformedJwtException exception) {
            throw new MalformedJwtException("Invalid JWT token " + exception.getMessage());
        } catch (Exception exception) {
            throw new JwtException(exception.getMessage());
        }
    }
}
