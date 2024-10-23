package com.notic.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.notic.accesstoken.persistence.entities.AccessToken;
import com.notic.accesstoken.business.services.IAccessTokenService;
import com.notic.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final IAccessTokenService accessTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(7); //Get token of authorization
            DecodedJWT decodeToken = jwtService.decodeToken(token); //Valid signature and expiration
            AccessToken accessToken = accessTokenService.getAccessTokenByToken(token);
            if(jwtService.isTokenValid(accessToken)){
                Authentication userAuthenticate =
                        new UsernamePasswordAuthenticationToken(decodeToken.getSubject(), null, null);

                SecurityContextHolder.getContext().setAuthentication(userAuthenticate);
            }
        }
        filterChain.doFilter(request, response);
    }
}
