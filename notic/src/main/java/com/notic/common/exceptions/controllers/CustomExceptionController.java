package com.notic.common.exceptions.controllers;

import com.notic.common.dtos.responses.ExceptionResponseDTO;
import com.notic.common.exceptions.customexceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionResponseDTO> customException(CustomException customException){

        Map<String, String> error = Map.of(
          "Title", customException.getTitle(),
          "Message", customException.getMessage()
        );

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
               customException.getStatus().value(), error
        );

        return ResponseEntity.status(customException.getStatus()).body(exceptionResponseDTO);
    }

}
