package com.notic.common.exceptions.customexceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExists extends CustomException{
    public AlreadyExists(String title, String message, HttpStatus status) {
        super(title, message, status);
    }
}
