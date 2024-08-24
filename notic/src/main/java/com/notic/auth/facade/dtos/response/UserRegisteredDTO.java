package com.notic.auth.facade.dtos.response;

public record UserRegisteredDTO(
    String email,
    String names,
    String surnames,
    Boolean active
) { }
