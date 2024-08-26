package com.notic.common.dtos.responses;

import lombok.Getter;

import java.util.Map;

@Getter
public class ExceptionResponseDTO extends ResponseDTO{

    private final ErrorResponseDTO errors;

    public ExceptionResponseDTO(Integer status, ErrorResponseDTO errors) {
        super(Boolean.FALSE, status);
        this.errors = errors;
    }

}
