package com.notic.auth.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthenticationExceptionConstants {

    EMAIL_ALREADY_EXISTS("Email already registered", "There is already a user registered with this Email", HttpStatus.CONFLICT);


    private final String title;
    private final String message;
    private final HttpStatus status;

     AuthenticationExceptionConstants(String title, String message, HttpStatus status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }
}
