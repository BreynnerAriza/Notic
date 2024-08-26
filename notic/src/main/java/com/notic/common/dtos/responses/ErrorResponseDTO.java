package com.notic.common.dtos.responses;

import java.io.Serializable;
import java.util.List;

public record ErrorResponseDTO(
        String title,
        List<String> messages
) implements Serializable {

}
