package com.notic.common.exceptions.customexceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private final String title;
    private final HttpStatus status;

    public CustomException(String title, String message, HttpStatus status){
        super(message);
        this.title = title;
        this.status = status;
    }

}
