package com.notic.common.dtos.responses;

import lombok.Getter;

import java.util.Map;

@Getter
public class ExceptionResponseDTO extends ResponseDTO{

    private final Map<String, String> error;

    public ExceptionResponseDTO(Integer status, Map<String, String> error) {
        super(Boolean.FALSE, status);
        this.error = error;
    }

}
