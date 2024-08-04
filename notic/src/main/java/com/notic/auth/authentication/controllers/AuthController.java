package com.notic.auth.authentication.controllers;

import com.notic.auth.authentication.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;
import com.notic.auth.authentication.services.IAuthenticationService;
import com.notic.common.dtos.responses.ResponseDTO;
import com.notic.common.dtos.responses.SuccessResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> authenticate(
            @RequestBody AuthenticationCredentialsDTO credentials){
        AuthenticationSuccessDTO authentication = authenticationService.authentication(credentials);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.OK.value(), authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> register(
            @RequestBody UserRegisterDTO userRegisterDTO){
        AuthenticationSuccessDTO authentication = authenticationService.register(userRegisterDTO);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
