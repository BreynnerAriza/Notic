package com.notic.auth.authentication.dtos.response;

public record UserRegisteredDTO(
    String email,
    String names,
    String surnames,
    Boolean Active
) { }
