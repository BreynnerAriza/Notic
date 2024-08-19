package com.notic.auth.controllers;

import com.notic.auth.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.dtos.request.RefreshTokenDTO;
import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.auth.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.dtos.response.LogoutSuccessDTO;
import com.notic.auth.services.IAuthenticationService;
import com.notic.common.dtos.responses.SuccessResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> authenticate(
            @RequestBody AuthenticationCredentialsDTO credentials
    ){
        AuthenticationSuccessDTO authentication = authenticationService.authentication(credentials);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.OK.value(), authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> register(
            @RequestBody UserRegisterDTO userRegisterDTO
    ){
        AuthenticationSuccessDTO authentication = authenticationService.register(userRegisterDTO);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/refresh-token/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> refreshToken(
            @RequestBody RefreshTokenDTO refreshToken
    ){
        AuthenticationSuccessDTO authentication = authenticationService.refreshToken(refreshToken);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/logout/")
    public ResponseEntity<SuccessResponseDTO<LogoutSuccessDTO>> logout(HttpServletRequest request){
        LogoutSuccessDTO logoutSuccess = authenticationService.logout(request);

        SuccessResponseDTO<LogoutSuccessDTO> response =
                new SuccessResponseDTO<>(HttpStatus.OK.value(), logoutSuccess);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
