package com.notic.auth.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.notic.accesstoken.constants.TokenType;
import com.notic.accesstoken.domain.AccessToken;
import com.notic.accesstoken.services.IAccessTokenService;
import com.notic.auth.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.dtos.request.RefreshTokenDTO;
import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.auth.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.dtos.response.LogoutSuccessDTO;
import com.notic.common.exceptions.customexceptions.TokenInvalidException;
import com.notic.common.security.services.JwtService;
import com.notic.user.domain.User;
import com.notic.user.dtos.response.UserResponseDTO;
import com.notic.user.mappers.UserMapper;
import com.notic.user.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.notic.common.exceptions.constants.ExceptionConstants.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IAccessTokenService accessTokenService;
    private final UserMapper userMapper;

    @Override
    public AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO) {

        final User userSave = userService.saveUser(userRegisterDTO, passwordEncoder.encode(userRegisterDTO.password()));

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

        if(accessTokenService.getAccessTokenByToken(token.refreshToken()) != null){
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
        accessToken.setExpired(Boolean.TRUE);
        accessToken.setRevoked(Boolean.TRUE);
        accessTokenService.saveToken(accessToken);
        SecurityContextHolder.clearContext();
        return new LogoutSuccessDTO("Logout successful");
    }

    @Override
    public UserResponseDTO getUserAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAuthenticate = userService.findUserByEmail(authentication.getPrincipal().toString());
        return userMapper.userToUserResponse(userAuthenticate);
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
