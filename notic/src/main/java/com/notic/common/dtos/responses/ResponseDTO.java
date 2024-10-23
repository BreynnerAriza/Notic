package com.notic.common.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ResponseDTO implements Serializable {

    @Schema(description = "Response status", example = "true")
    private final Boolean success;

    @Schema(description = "Response status code", example = "200")
    private final Integer status;

    @Schema(description = "Date response")
    private final LocalDateTime date;

    public ResponseDTO(Boolean success, Integer status) {
        this.success = success;
        this.status = status;
        this.date = LocalDateTime.now();
    }

}
