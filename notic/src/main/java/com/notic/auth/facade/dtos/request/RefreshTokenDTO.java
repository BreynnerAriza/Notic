package com.notic.auth.facade.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record RefreshTokenDTO (

        @NotBlank(message = REFRESH_TOKEN_REQUIRED)
        String refreshToken

) implements Serializable {

    private static final String REFRESH_TOKEN_REQUIRED = "The refresh token is required";

}
