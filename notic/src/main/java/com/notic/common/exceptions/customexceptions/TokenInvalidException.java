package com.notic.common.exceptions.customexceptions;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends CustomException{

    public TokenInvalidException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }

}
