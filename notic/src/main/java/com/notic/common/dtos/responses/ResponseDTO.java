package com.notic.common.dtos.responses;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ResponseDTO implements Serializable {

    private final Boolean success;
    private final Integer status;
    private final LocalDateTime date;

    public ResponseDTO(Boolean success, Integer status) {
        this.success = success;
        this.status = status;
        this.date = LocalDateTime.now();
    }

}
