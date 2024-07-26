package com.notic.auth.authentication.services;

import com.notic.auth.authentication.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;

public interface AuthenticationService {

    AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO );
    AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO);

}
