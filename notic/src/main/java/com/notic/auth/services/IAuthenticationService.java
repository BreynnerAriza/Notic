package com.notic.auth.services;

import com.notic.auth.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.dtos.request.RefreshTokenDTO;
import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.auth.dtos.response.AuthenticationSuccessDTO;

public interface IAuthenticationService {

    AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO );
    AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO);
    AuthenticationSuccessDTO refreshToken(RefreshTokenDTO token);

}
