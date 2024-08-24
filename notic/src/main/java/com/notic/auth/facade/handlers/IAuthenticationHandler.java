package com.notic.auth.facade.handlers;

import com.notic.auth.facade.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.facade.dtos.request.RefreshTokenDTO;
import com.notic.auth.facade.dtos.request.UserRegisterDTO;
import com.notic.auth.facade.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.facade.dtos.response.LogoutSuccessDTO;
import com.notic.user.persistence.entities.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationHandler {

    AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO );
    AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO);
    AuthenticationSuccessDTO refreshToken(RefreshTokenDTO token);
    LogoutSuccessDTO logout(HttpServletRequest httpRequest);
    User getUserAuthenticate();

}
