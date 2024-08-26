package com.notic.auth.facade.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserRegisterDTO (


    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    @Size(max = EMAIL_MAX_LENGTH, message = EMAIL_MAX_LENGTH_INVALID)
    String email,
    @NotBlank(message = PASSWORD_REQUIRED)
    String password,
    @NotBlank(message = NAMES_REQUIRED)
    @Size(max = NAMES_MAX_LENGTH, message = NAME_MAX_LENGTH_INVALID)
    String names,
    @NotBlank(message = SURNAMES_REQUIRED)
    @Size(max = SURNAMES_MAX_LENGTH, message = SURNAMES_MAX_LENGTH_INVALID)
    String surnames

) implements Serializable {

    private static final String EMAIL_REQUIRED = "The email is required";
    private static final String EMAIL_FORMAT_INVALID = "The format of email is invalid";
    private static final int EMAIL_MAX_LENGTH = 100;
    private static final String EMAIL_MAX_LENGTH_INVALID = "The email cannot be longer than " + EMAIL_MAX_LENGTH + " characters";

    private static final String PASSWORD_REQUIRED ="The password is required";

    private static final String NAMES_REQUIRED = "The name is required";
    private static final int NAMES_MAX_LENGTH = 100;
    private static final String NAME_MAX_LENGTH_INVALID = "The names cannot be longer than " + NAMES_MAX_LENGTH + " characters";

    private static final String SURNAMES_REQUIRED = "The surname is required";
    private static final int SURNAMES_MAX_LENGTH = 100;
    private static final String SURNAMES_MAX_LENGTH_INVALID = "The surname cannot be longer than " + NAMES_MAX_LENGTH + " characters";

}
