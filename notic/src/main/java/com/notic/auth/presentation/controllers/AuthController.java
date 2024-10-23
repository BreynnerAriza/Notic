package com.notic.auth.presentation.controllers;

import com.notic.auth.facade.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.facade.dtos.request.RefreshTokenDTO;
import com.notic.auth.facade.dtos.request.UserRegisterDTO;
import com.notic.auth.facade.dtos.response.AuthenticationSuccessDTO;
import com.notic.auth.facade.dtos.response.LogoutSuccessDTO;
import com.notic.auth.facade.handlers.IAuthenticationHandler;
import com.notic.common.dtos.responses.ExceptionResponseDTO;
import com.notic.common.dtos.responses.SuccessResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication", description = "Controller for authentication")
public class AuthController {

    private final IAuthenticationHandler authenticationHandler;



    @PostMapping("/authenticate/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> authenticate(
            @Valid @RequestBody AuthenticationCredentialsDTO credentials
    ) {

        AuthenticationSuccessDTO authentication = authenticationHandler.authentication(credentials);
        SuccessResponseDTO<AuthenticationSuccessDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(
            summary = "Register for user",
            description = "Method for users to register themselves"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Register success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already registered"
            )
    })
    @PostMapping("/register/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> register(
            @Valid @RequestBody(required = true) UserRegisterDTO userRegisterDTO
    ){
        AuthenticationSuccessDTO authentication = authenticationHandler.register(userRegisterDTO);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/refresh-token/")
    public ResponseEntity<SuccessResponseDTO<AuthenticationSuccessDTO>> refreshToken(
           @Valid @RequestBody RefreshTokenDTO refreshToken
    ){
        AuthenticationSuccessDTO authentication = authenticationHandler.refreshToken(refreshToken);

        SuccessResponseDTO<AuthenticationSuccessDTO> response
                = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/logout/")
    public ResponseEntity<SuccessResponseDTO<LogoutSuccessDTO>> logout(HttpServletRequest request){
        LogoutSuccessDTO logoutSuccess = authenticationHandler.logout(request);

        SuccessResponseDTO<LogoutSuccessDTO> response =
                new SuccessResponseDTO<>(HttpStatus.OK.value(), logoutSuccess);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
