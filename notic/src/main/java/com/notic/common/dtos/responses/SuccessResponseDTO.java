package com.notic.common.dtos.responses;

import lombok.Getter;

@Getter
public class SuccessResponseDTO<T> extends ResponseDTO{

    private final T data;

    public SuccessResponseDTO(Integer status, T data) {
        super(Boolean.TRUE, status);
        this.data = data;
    }

}
