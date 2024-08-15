package com.notic.common.exceptions.customexceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException{

    public NotFoundException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }

}
