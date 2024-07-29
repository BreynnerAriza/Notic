package com.notic.common.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.private.key}")
    private String privateKey;
    @Value("${jwt.user}")
    private String userGenerator;
    @Value("${jwt.expiration}")
    private long tokenExpiration; // 1 Day
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpiration; // 7 Day

    public String getToken(UserDetails userDetails){
        return buildToken(userDetails, tokenExpiration);
    }

    public String getRefreshToken(UserDetails userDetails){
        return buildToken(userDetails, refreshTokenExpiration);
    }

    private String buildToken(UserDetails userDetails, long expiration){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(userDetails.getUsername())
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        try{
            JWTVerifier jwtVerifier  = JWT
                    .require(algorithm)
                    .withIssuer(userGenerator)
                    .build();
            return jwtVerifier.verify(token);
        }catch(TokenExpiredException tokenExpiredException){
            System.out.println("The token is expired");
        }
        return null;
    }

}
