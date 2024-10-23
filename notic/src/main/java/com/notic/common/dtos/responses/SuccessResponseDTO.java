package com.notic.common.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Schema(title = "Success response", description = "Correct response to a request")
@Getter
public class SuccessResponseDTO<T> extends ResponseDTO{

    private final T data;

    public SuccessResponseDTO(Integer status, T data) {
        super(Boolean.TRUE, status);
        this.data = data;
    }

}
