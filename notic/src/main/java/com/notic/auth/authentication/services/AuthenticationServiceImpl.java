package com.notic.auth.authentication.services;

import com.notic.accesstoken.constants.TokenType;
import com.notic.accesstoken.domain.AccessToken;
import com.notic.accesstoken.services.IAccessTokenService;
import com.notic.auth.authentication.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.AuthenticationSuccessDTO;
import static com.notic.auth.authentication.exceptions.constants.AuthenticationExceptionConstants.*;
import com.notic.common.exceptions.customexceptions.AlreadyExists;
import com.notic.common.security.services.JwtService;
import com.notic.user.domain.User;
import com.notic.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IAccessTokenService accessTokenService;

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
