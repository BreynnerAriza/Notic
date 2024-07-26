package com.notic.auth.authentication.services;

import com.notic.accesstoken.services.AccessTokenService;
import com.notic.auth.authentication.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.AuthenticationSuccessDTO;
import com.notic.user.domain.User;
import com.notic.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;

    @Override
    public AuthenticationSuccessDTO register(UserRegisterDTO userRegisterDTO) {
        if(userService.findUserByEmail(userRegisterDTO.email()) != null){
            // Throw exception email already exits
        }
        final User userSave = userService.saveUser
                (userRegisterDTO, passwordEncoder.encode(userRegisterDTO.password()));
        //Generated token and save token
        //Return access token and refresh token
        return null;
    }

    @Override
    public AuthenticationSuccessDTO authentication(AuthenticationCredentialsDTO authenticationCredentialsDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationCredentialsDTO.email(),
                    authenticationCredentialsDTO.password()
                )
        );
        //Generated token and save token
        //Return access token and refresh token
        return null;
    }


}
