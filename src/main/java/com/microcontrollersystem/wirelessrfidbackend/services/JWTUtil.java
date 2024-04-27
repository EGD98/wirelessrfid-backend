package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.configuration.Security;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;


@Component
@AllArgsConstructor
public class JWTUtil {

    private final Security security;
    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public String create(String id, String subject) {

        // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
        byte[] apiKeySecretBytes = security.getSecret().getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(security.getIssuer())
                .signWith(signatureAlgorithm, signingKey);

        if (security.getTtlMillis() >= 0) {
            long expMillis = nowMillis + security.getTtlMillis();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(security.getSecret().getBytes())
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getKey(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(security.getSecret().getBytes())
                .parseClaimsJws(jwt).getBody();

        return claims.getId();
    }

    public void validateToken(String token) throws Exception {
        if (token == null || !token.startsWith("Bearer ")) {
            // El token no está presente o no está en el formato esperado
            throw new Exception("Token no válido");
        }

        String jwtToken = token.substring(7); // Eliminamos "Bearer " del inicio del token

        try {
            // Validar el token
            Claims claims = Jwts.parser()
                    .setSigningKey(security.getSecret().getBytes("UTF-8"))
                    .parseClaimsJws(jwtToken)
                    .getBody();
            Instant tokenExpiration = claims.getExpiration().toInstant();

            if (tokenExpiration.isBefore(Instant.now())){
                throw new Exception("TOKEN EXPIRADO!");
            }
            // Aquí puedes hacer lo que necesites con las claims del token
            // Por ejemplo, obtener información del usuario, roles, etc.

        } catch (JwtException | IllegalArgumentException e) {
            // Capturar cualquier excepción relacionada con la validación del token
            throw new Exception("ERROR AL VALIDAR EL TOKEN");
        }
    }

    public Integer getTokenIdUser(String token) throws Exception {

        String jwtToken = token.substring(7); // Eliminamos "Bearer " del inicio del token
        try {
            // Validar el token
            Claims claims = Jwts.parser()
                    .setSigningKey(security.getSecret().getBytes("UTF-8"))
                    .parseClaimsJws(jwtToken)
                    .getBody();
            Instant tokenExpiration = claims.getExpiration().toInstant();
            return Integer.valueOf(claims.getId());
        } catch (JwtException | IllegalArgumentException e) {
            // Capturar cualquier excepción relacionada con la validación del token
            throw new Exception("ERROR AL VALIDAR EL TOKEN");
        }
    }

}