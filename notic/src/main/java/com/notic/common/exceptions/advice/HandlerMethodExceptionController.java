package com.notic.common.exceptions.advice;

import com.notic.common.dtos.responses.ErrorResponseDTO;
import com.notic.common.dtos.responses.ExceptionResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerMethodExceptionController {

    private static final String TITLE_ERROR = "Error in the request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorResponseDTO errors = new ErrorResponseDTO(TITLE_ERROR, ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(),errors));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponseDTO errors = new ErrorResponseDTO(TITLE_ERROR, List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(),errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponseDTO errors = new ErrorResponseDTO(TITLE_ERROR, List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(),errors));
    }


}
