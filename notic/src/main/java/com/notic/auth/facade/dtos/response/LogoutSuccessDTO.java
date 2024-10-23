package com.notic.auth.facade.dtos.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(title = "Logout success", description = "Response to a completed logout")
public record LogoutSuccessDTO(

    @Schema(example = "Logout successful")
    String message

) implements Serializable { }
