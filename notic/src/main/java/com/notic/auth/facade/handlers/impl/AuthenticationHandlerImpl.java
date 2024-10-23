package com.notic.auth.facade.handlers.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.notic.accesstoken.business.services.IAccessTokenService;
import com.notic.accesstoken.persistence.entities.AccessToken;
import com.notic.accesstoken.utils.constants.TokenType;
import com.notic.auth.facade.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.facade.dtos.request.RefreshTokenDTO;
import com.notic.auth.facade.dtos.request.UserRegisterDTO;
import com.notic.auth.facade.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.facade.dtos.response.LogoutSuccessDTO;
import com.notic.auth.facade.handlers.IAuthenticationHandler;
import com.notic.common.exceptions.customexceptions.TokenInvalidException;
import com.notic.security.services.JwtService;
import com.notic.user.business.services.IUserService;
import com.notic.user.facade.mappers.UserMapper;
import com.notic.user.persistence.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.notic.common.exceptions.constants.ExceptionConstants.TOKEN_INVALID;

@Service
@RequiredArgsConstructor
public class AuthenticationHandlerImpl implements IAuthenticationHandler {

    private final IUserService userService;
    private final JwtService jwtService;
    private final IAccessTokenService accessTokenService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.userRegisterToUser(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

        final User userSave = userService.saveUser(user);
        final String token = jwtService.getToken(userSave);
        final String refreshToken = jwtService.getRefreshToken(userSave);
        saveTokenUser(userSave, token); //Save token

        return new AuthenticationSuccessDTO(token, refreshToken);
    }

    @Override
    public AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationCredentialsDTO.email(),
                    authenticationCredentialsDTO.password()
                )
        );
        User user = userService.findUserByEmail(authenticationCredentialsDTO.email());
        final String token = jwtService.getToken(user);
        final String refreshToken = jwtService.getRefreshToken(user);
        saveTokenUser(user, token); //Save token

        return new AuthenticationSuccessDTO(token, refreshToken);
    }

    @Override
    public AuthenticationSuccessDTO refreshToken(RefreshTokenDTO token) {

        if(accessTokenService.isRefreshToken(token.refreshToken())){
            throw new TokenInvalidException(
                    TOKEN_INVALID.getTitle(), TOKEN_INVALID.getMessage(), TOKEN_INVALID.getStatus()
            );
        }

        DecodedJWT decodedJWT = jwtService.decodeToken(token.refreshToken());
        User user = userService.findUserByEmail(decodedJWT.getSubject());
        String newToken = jwtService.getToken(user);
        saveTokenUser(user, newToken);

        return new AuthenticationSuccessDTO(newToken, token.refreshToken());
    }

    @Override
    public LogoutSuccessDTO logout(HttpServletRequest httpRequest) {

        String bearerToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = bearerToken.substring(7);
        AccessToken accessToken = accessTokenService.getAccessTokenByToken(token);
        accessTokenService.invalidatedToken(accessToken);
        SecurityContextHolder.clearContext();
        return new LogoutSuccessDTO("Logout successful");

    }

    @Override
    public User getUserAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(authentication.getPrincipal().toString());
    }

    private void saveTokenUser(User user, String token){
        final AccessToken accessToken = AccessToken.builder()
                .token(token)
                .expired(Boolean.FALSE)
                .revoked(Boolean.FALSE)
                .tokenType(TokenType.BEARER)
                .user(user)
                .build();
        accessTokenService.saveToken(accessToken);
    }

}
