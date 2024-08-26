package com.notic.auth.facade.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticationCredentialsDTO(
        @NotBlank(message = EMAIL_REQUIRED)
        String email,
        @NotBlank(message = PASSWORD_REQUIRED)
        String password
) implements Serializable {
    private static final String EMAIL_REQUIRED = "The email is required";
    private static final String PASSWORD_REQUIRED = "The password is required";
}
