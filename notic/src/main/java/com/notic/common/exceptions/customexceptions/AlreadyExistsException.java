package com.notic.common.exceptions.customexceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends CustomException{
    public AlreadyExistsException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }
}
