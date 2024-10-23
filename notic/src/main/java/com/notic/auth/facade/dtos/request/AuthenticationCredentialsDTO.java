package com.notic.auth.facade.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Schema(title = "Credentials", description = "Credentials required for authentication")
public record AuthenticationCredentialsDTO(

        @Schema(description = "User email", example = "example@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = EMAIL_REQUIRED)
        String email,

        @Schema(description = "User password", example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = PASSWORD_REQUIRED)
        String password

) implements Serializable {

    private static final String EMAIL_REQUIRED = "The email is required";
    private static final String PASSWORD_REQUIRED = "The password is required";

}
