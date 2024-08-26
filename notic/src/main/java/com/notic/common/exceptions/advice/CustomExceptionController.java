package com.notic.common.exceptions.advice;

import com.notic.common.dtos.responses.ErrorResponseDTO;
import com.notic.common.dtos.responses.ExceptionResponseDTO;
import com.notic.common.exceptions.customexceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionController {

    private static final String TITLE_ERROR = "Error in the request";

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionResponseDTO> customException(CustomException customException){

        ErrorResponseDTO errors = new ErrorResponseDTO(customException.getTitle(), List.of(customException.getMessage()));
        return ResponseEntity.status(customException.getStatus()).body(new ExceptionResponseDTO(customException.getStatus().value(), errors));

    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ExceptionResponseDTO> dateTimeException(DateTimeException dateTimeException){

        ErrorResponseDTO errors = new ErrorResponseDTO(TITLE_ERROR, List.of(dateTimeException.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), errors));

    }
}
