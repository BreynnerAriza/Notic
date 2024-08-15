package com.notic.auth.services;

import com.notic.auth.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.dtos.request.RefreshTokenDTO;
import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.auth.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.dtos.response.LogoutSuccessDTO;
import com.notic.user.dtos.response.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface IAuthenticationService {

    AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO );
    AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO);
    AuthenticationSuccessDTO refreshToken(RefreshTokenDTO token);
    LogoutSuccessDTO logout(HttpServletRequest httpRequest);
    UserResponseDTO getUserAuthenticate();


}
