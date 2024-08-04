package com.notic.common.exceptions.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionConstants {

    TOKEN_INVALID("The token is invalid","The token is invalid",HttpStatus.FORBIDDEN);

    private final String title;
    private final String message;
    private final HttpStatus status;

    ExceptionConstants(String title, String message, HttpStatus status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

}
