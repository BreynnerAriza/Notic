package com.notic.auth.dtos.response;

public record UserRegisteredDTO(
    String email,
    String names,
    String surnames,
    Boolean active
) { }
